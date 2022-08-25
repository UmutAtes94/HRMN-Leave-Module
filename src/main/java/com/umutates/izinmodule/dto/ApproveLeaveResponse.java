package com.umutates.izinmodule.dto;

public class ApproveLeaveResponse {
    private String statusShortCode;
    private String status;
    private Integer leavesAmount;
    private Integer remainingLeavesAmount;

    public String getStatusShortCode() {
        return statusShortCode;
    }

    public void setStatusShortCode(String statusShortCode) {
        this.statusShortCode = statusShortCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLeavesAmount() {
        return leavesAmount;
    }

    public void setLeavesAmount(Integer leavesAmount) {
        this.leavesAmount = leavesAmount;
    }

    public Integer getRemainingLeavesAmount() {
        return remainingLeavesAmount;
    }

    public void setRemainingLeavesAmount(Integer remainingLeavesAmount) {
        this.remainingLeavesAmount = remainingLeavesAmount;
    }
}
