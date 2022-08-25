package com.umutates.izinmodule.service;

import com.umutates.izinmodule.base.GeneralEnumeration.*;
import com.umutates.izinmodule.dto.ApproveLeaveRequest;
import com.umutates.izinmodule.dto.Employee;
import com.umutates.izinmodule.dto.LeaveRequest;
import com.umutates.izinmodule.exception.EmployeeNotFoundException;
import com.umutates.izinmodule.exception.EmployeeRoleException;
import com.umutates.izinmodule.exception.InvalidRequestException;
import com.umutates.izinmodule.model.LeavesRequestStatusEntity;
import com.umutates.izinmodule.repository.LeaveRequestStatusRepository;
import com.umutates.izinmodule.repository.LeavesCalculationRuleRepository;
import com.umutates.izinmodule.utils.DateUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class LeavesServiceImpl implements ILeavesService {

    private final LeavesCalculationRuleRepository leavesCalculationRuleRepository;
    private final LeaveRequestStatusRepository leaveRequestStatusRepository;
    private final IEmployeeService employeeService;

    public LeavesServiceImpl(LeavesCalculationRuleRepository leavesCalculationRuleRepository, LeaveRequestStatusRepository leaveRequestStatusRepository, @Lazy IEmployeeService employeeService) {
        this.leavesCalculationRuleRepository = leavesCalculationRuleRepository;
        this.leaveRequestStatusRepository = leaveRequestStatusRepository;
        this.employeeService = employeeService;
    }

    public Integer processLeavesCalculationRules(LocalDate workStartDate) {

        int workingForYears = DateUtils.getYearsBetweenCurrentDateAndGivenDate(workStartDate);
        Integer numberOfLeaves = leavesCalculationRuleRepository.findLeavesValueByBetweenMaxAndMin(workingForYears);

        if (numberOfLeaves == null)
            return 0;

        return numberOfLeaves;
    }


    @Transactional
    public void createNewLeave(LeaveRequest leaveRequest) {
        this.validateLeaveRequest(leaveRequest);

        //TODO:: izin almak istediği tarihler arasında onaylanmış izni var mı kontrolü yapılabilir

        Employee employee = this.employeeService.findEmployeeById(leaveRequest.getEmployeeId());
        if (employee == null)
            throw new EmployeeNotFoundException("Can not find employee from given ID");

        int requestedLeavesAmount = DateUtils.findLeavesAmountBetweenTwoDate(leaveRequest.getLeaveStartDate(), leaveRequest.getLeaveEndDate());
        int workingYear = DateUtils.getYearsBetweenCurrentDateAndGivenDate(employee.getStartDate());

        //TODO:yönetici onayında bekleyen izin varsa o bekleyen izin + yeni gelen izin izin hakkından büyük olamaz kontrolü yapılmalı
        this.validateCustomerLeaveAmountByWorkingYearAndLeaveAmount(employee.getLeavesAmount(), workingYear, requestedLeavesAmount);

        this.createLeaveRequestStatusRecord(leaveRequest, requestedLeavesAmount);
    }

    private void validateLeaveRequest(LeaveRequest leaveRequest) {
        if (leaveRequest.getEmployeeId() == null)
            throw new InvalidRequestException("EmployeeId cannot be null");

        if (leaveRequest.getLeaveStartDate() == null || leaveRequest.getLeaveEndDate() == null)
            throw new InvalidRequestException("Dates cannot be null");

        if (leaveRequest.getLeaveEndDate().isBefore(leaveRequest.getLeaveStartDate()))
            throw new InvalidRequestException("Leave-End-Date can not before Start-Date");

        if (leaveRequest.getLeaveStartDate().equals(leaveRequest.getLeaveEndDate()))
            throw new InvalidRequestException("Start date and End date can not be same day.");

        if (leaveRequest.getLeaveStartDate().isBefore(LocalDate.now()) || leaveRequest.getLeaveEndDate().isBefore(LocalDate.now()))
            throw new InvalidRequestException("Start date or end date can not before now");
    }

    private void validateCustomerLeaveAmountByWorkingYearAndLeaveAmount(int employeeLeaveAmount, int employeeWorkingYear, int requestedLeaveAmount) {
        if (employeeLeaveAmount <= 0 && employeeWorkingYear < 1) {
            if (AvanceLeaveAmount.WORKING_FOR_1_YEAR.getAmount() > employeeLeaveAmount)
                throw new InvalidRequestException("Employee has not enough leaveAmount to take new leaves");
            if (!(AvanceLeaveAmount.WORKING_FOR_1_YEAR.getAmount() <= employeeLeaveAmount - requestedLeaveAmount)) {
                throw new InvalidRequestException("The employee can take leaves for a total of -5 days who has been working for 1 year");
            }
        } else if (employeeWorkingYear >= 1 && !(requestedLeaveAmount <= employeeLeaveAmount)) {
            throw new InvalidRequestException("Employee can not take leaves more then current leave amount");
        }
    }

    private void createLeaveRequestStatusRecord(LeaveRequest leaveRequest, int leavesAmount) {
        //chek if there existing pending status
        LeavesRequestStatusEntity existingLeaveRequestStatus =
                leaveRequestStatusRepository.findByEmployeeIdAndStatusShortCode(leaveRequest.getEmployeeId(),
                        LeaveRequestStatus.ONAY_BEKLIYOR.toString());
        if (existingLeaveRequestStatus != null) {
            existingLeaveRequestStatus.setRequestedLeaveAmount(leavesAmount);
            leaveRequestStatusRepository.save(existingLeaveRequestStatus);
            return;
        }

        LeavesRequestStatusEntity leavesRequestStatusEntity = new LeavesRequestStatusEntity();
        leavesRequestStatusEntity.setRequestedLeaveAmount(leavesAmount);
        leavesRequestStatusEntity.setEmployeeId(leaveRequest.getEmployeeId());

        leavesRequestStatusEntity.setStatusShortCode(LeaveRequestStatus.ONAY_BEKLIYOR.toString());
        leavesRequestStatusEntity.setStatus(LeaveRequestStatus.ONAY_BEKLIYOR.getDescription());
        leaveRequestStatusRepository.save(leavesRequestStatusEntity);

    }

    @Transactional
    public void approveLeaveRequest(ApproveLeaveRequest approveLeaveRequest) {
        this.validateApproveLeaveRequest(approveLeaveRequest);

        if (this.verifyEmployeeRole(approveLeaveRequest.getLoggedInEmployeeId()))
            throw new EmployeeRoleException("Logged-In requester has not a permission to approve leave request");

        Employee employee = employeeService.findEmployeeById(approveLeaveRequest.getEmployeeId());

        if (employee == null)
            throw new EmployeeNotFoundException("Employee can not be found");

        LeavesRequestStatusEntity leaveRequestStatusEntity = leaveRequestStatusRepository.findByEmployeeIdAndStatusShortCode(employee.getEmployeeId(), LeaveRequestStatus.ONAY_BEKLIYOR.toString());


        LeaveRequestStatus leaveStatus = LeaveRequestStatus.findByShortCode(approveLeaveRequest.getStatusShortCode());
        if (leaveStatus == null)
            throw new InvalidRequestException("Invalid Status Shortcode");

        leaveRequestStatusEntity.setStatusShortCode(leaveStatus.getShortCode());
        leaveRequestStatusEntity.setStatus(leaveStatus.getDescription());
        leaveRequestStatusRepository.save(leaveRequestStatusEntity);

        if (LeaveRequestStatus.ONAYLANDI.getShortCode().equalsIgnoreCase(approveLeaveRequest.getStatusShortCode()))
            employeeService.updateEmployeeLeaveAmount(employee.getEmployeeId(), employee.getLeavesAmount() - leaveRequestStatusEntity.getRequestedLeaveAmount());

    }

    private void validateApproveLeaveRequest(ApproveLeaveRequest approveLeaveRequest) {
        if (approveLeaveRequest.getEmployeeId() == null)
            throw new InvalidRequestException("employeeId can not be null");

        boolean isValidLeaveRequestStatus = LeaveRequestStatus.leaveRequestStatusList().stream()
                .anyMatch(leaveRequestStatus -> leaveRequestStatus.toString().equalsIgnoreCase(approveLeaveRequest.getStatusShortCode()));
        if (!isValidLeaveRequestStatus)
            throw new InvalidRequestException("Leave request status invalid or null");

        if (approveLeaveRequest.getLoggedInEmployeeId() == null)
            throw new InvalidRequestException("Logged-in employee can not be null");

    }

    private boolean verifyEmployeeRole(Long employeeId) {
        List<String> loggedInEmployeeRoles = employeeService.findEmployeeRolesByEmployeeId(employeeId);
        //TODO:: rolü yoksa hata fırlat.
        return loggedInEmployeeRoles.stream()
                .anyMatch(role -> Roles.ADMIN.toString().equalsIgnoreCase(role));
    }

}
