package com.umutates.izinmodule.service;

import com.umutates.izinmodule.dto.CreateEmployeeRequest;
import com.umutates.izinmodule.dto.Employee;

import java.util.List;

public interface IEmployeeService {

    Employee createEmployee(CreateEmployeeRequest createEmployeeRequest);

    Employee findEmployeeById(Long employeeId);

    List<String> findEmployeeRolesByEmployeeId(Long employeeId);

    void updateEmployeeLeaveAmount(Long employeeId, Integer newLeaveAmount);
}
