package com.umutates.izinmodule.controller;


import com.umutates.izinmodule.dto.CreateEmployeeRequest;
import com.umutates.izinmodule.dto.Employee;
import com.umutates.izinmodule.service.IEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

    private final IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody CreateEmployeeRequest createEmployeeRequest){
        return ResponseEntity.ok(this.employeeService.createEmployee(createEmployeeRequest));
    }
}
