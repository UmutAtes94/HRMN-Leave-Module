package com.umutates.izinmodule.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LeavesRequestStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long leavesRequestId;
    private Long employeeId;
    private String status;
    private String statusShortCode;
    private Integer requestedLeaveAmount;

    public Long getLeavesRequestId() {
        return leavesRequestId;
    }

    public void setLeavesRequestId(Long leavesRequestId) {
        this.leavesRequestId = leavesRequestId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusShortCode() {
        return statusShortCode;
    }

    public void setStatusShortCode(String statusShortCode) {
        this.statusShortCode = statusShortCode;
    }

    public Integer getRequestedLeaveAmount() {
        return requestedLeaveAmount;
    }

    public void setRequestedLeaveAmount(Integer requestedLeavesValue) {
        this.requestedLeaveAmount = requestedLeavesValue;
    }
}
