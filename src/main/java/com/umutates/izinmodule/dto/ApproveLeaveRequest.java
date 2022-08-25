package com.umutates.izinmodule.dto;

public class ApproveLeaveRequest {

    private Long employeeId;
    private String statusShortCode;
    private Long loggedInEmployeeId;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getStatusShortCode() {
        return statusShortCode;
    }

    public void setStatusShortCode(String statusShortCode) {
        this.statusShortCode = statusShortCode;
    }

    public Long getLoggedInEmployeeId() {
        return loggedInEmployeeId;
    }

    public void setLoggedInEmployeeId(Long loggedInEmployeeId) {
        this.loggedInEmployeeId = loggedInEmployeeId;
    }
}
