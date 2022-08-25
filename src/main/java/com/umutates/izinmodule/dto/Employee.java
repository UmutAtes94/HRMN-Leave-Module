package com.umutates.izinmodule.dto;

import java.time.LocalDate;
import java.util.List;

public class Employee {

    private Long employeeId;
    private String name;
    private String surname;
    private LocalDate startDate;
    private Integer leavesAmount;

    private List<String> roleNames;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getLeavesAmount() {
        return leavesAmount;
    }

    public void setLeavesAmount(Integer leavesAmount) {
        this.leavesAmount = leavesAmount;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}
