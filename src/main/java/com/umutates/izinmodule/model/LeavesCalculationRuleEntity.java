package com.umutates.izinmodule.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LeavesCalculationRuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer leavesCalculationRuleId;
    private String description;
    private Integer min;
    private Integer max;
    private Integer leavesValue;

    public Integer getLeavesCalculationRuleId() {
        return leavesCalculationRuleId;
    }

    public void setLeavesCalculationRuleId(Integer leavesCalculationRuleId) {
        this.leavesCalculationRuleId = leavesCalculationRuleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getLeavesValue() {
        return leavesValue;
    }

    public void setLeavesValue(Integer leavesValue) {
        this.leavesValue = leavesValue;
    }
}
