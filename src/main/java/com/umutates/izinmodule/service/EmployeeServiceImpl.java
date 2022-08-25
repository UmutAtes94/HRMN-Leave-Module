package com.umutates.izinmodule.service;


import com.umutates.izinmodule.base.GeneralEnumeration;
import com.umutates.izinmodule.dto.CreateEmployeeRequest;
import com.umutates.izinmodule.dto.Employee;
import com.umutates.izinmodule.exception.InvalidRequestException;
import com.umutates.izinmodule.mapper.EmployeeMapper;
import com.umutates.izinmodule.model.EmployeeEntity;
import com.umutates.izinmodule.model.EmployeeRoleEntity;
import com.umutates.izinmodule.model.RoleEntity;
import com.umutates.izinmodule.repository.EmployeeRepository;
import com.umutates.izinmodule.repository.EmployeeRoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final ILeavesService leavesService;
    private final EmployeeRoleRepository employeeRoleRepository;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper, EmployeeRepository employeeRepository, ILeavesService leavesService, EmployeeRoleRepository employeeRoleRepository) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
        this.leavesService = leavesService;
        this.employeeRoleRepository = employeeRoleRepository;
    }


    public Employee createEmployee(CreateEmployeeRequest createEmployeeRequest){
        this.validateCreateEmployeeRequest(createEmployeeRequest);

        EmployeeEntity newEmployeeEntity = EmployeeMapper.INSTANCE.createRequestToEmployeeEntity(createEmployeeRequest);

        Integer numberOfLeaves = leavesService.processLeavesCalculationRules(createEmployeeRequest.getStartDate());
        newEmployeeEntity.setNumberOfLeaves(numberOfLeaves);

        this.craeteEmployeeRoleByShortCodes(newEmployeeEntity, createEmployeeRequest.getRoles());

        return EmployeeMapper.INSTANCE.employeeEntityToEmployee(employeeRepository.save(newEmployeeEntity));

    }

    private void validateCreateEmployeeRequest(CreateEmployeeRequest createEmployeeRequest) {
        if (createEmployeeRequest.getStartDate() == null)
            throw new InvalidRequestException("Start date can not be null to CreateEmployeeRequest");

        if (createEmployeeRequest.getName() == null)
            throw new InvalidRequestException("Name can not be null to CreateEmployeeRequest");

        if (createEmployeeRequest.getSurname() == null)
            throw new InvalidRequestException("Surname can not be null to CreateEmployeeRequest");

    }

    public Employee findEmployeeById(Long employeeId){
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        return employeeEntity.map(EmployeeMapper.INSTANCE::employeeEntityToEmployee).orElse(null);
    }

    public List<String> findEmployeeRolesByEmployeeId(Long employeeId) {
        return employeeRoleRepository.findByEmployeeId(employeeId).stream()
                .map(EmployeeRoleEntity::getRoleEntity)
                .map(RoleEntity::getRoleShortCode)
                .collect(Collectors.toList());
    }

    //TODO::unit testler kaldı

    public void updateEmployeeLeaveAmount(Long employeeId, Integer newLeaveAmount){
        Optional<EmployeeEntity> employeeEntity = this.employeeRepository.findById(employeeId);
        employeeEntity.ifPresent( employee -> {
            employee.setNumberOfLeaves(newLeaveAmount);
            employeeRepository.save(employee);
        });
    }

    private void craeteEmployeeRoleByShortCodes(EmployeeEntity employeeEntity, List<String> roles) {

        List<EmployeeRoleEntity> employeeRoleEntities = new ArrayList<>();
        GeneralEnumeration.Roles.findRoleByShortCodes(roles).forEach(role -> {
            EmployeeRoleEntity employeeRoleEntity = new EmployeeRoleEntity();
            employeeRoleEntity.setRoleId(role.getRoleId());
            employeeRoleEntity.setCreateDate(new Date());
            employeeRoleEntities.add(employeeRoleEntity);
        });

        employeeEntity.setEmployeeRoleEntities(employeeRoleEntities);
    }

}
