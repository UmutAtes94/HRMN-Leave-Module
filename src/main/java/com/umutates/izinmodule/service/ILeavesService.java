package com.umutates.izinmodule.service;

import com.umutates.izinmodule.dto.ApproveLeaveRequest;
import com.umutates.izinmodule.dto.LeaveRequest;

import java.time.LocalDate;
import java.util.Date;

public interface ILeavesService {

    Integer processLeavesCalculationRules(LocalDate workStartDate);

    void createNewLeave(LeaveRequest leaveRequest);

    void approveLeaveRequest(ApproveLeaveRequest approveLeaveRequest);
}
