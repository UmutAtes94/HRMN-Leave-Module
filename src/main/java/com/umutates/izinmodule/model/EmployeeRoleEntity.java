package com.umutates.izinmodule.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class EmployeeRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeRoleId;
    private Long employeeId;
    private Integer roleId;
    private Date createDate;

    @OneToOne
    @JoinColumn(name = "roleId", referencedColumnName = "roleId",
    insertable=false, updatable = false)
    private RoleEntity roleEntity;


    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public Long getEmployeeRoleId() {
        return employeeRoleId;
    }

    public void setEmployeeRoleId(Long employeeRoleId) {
        this.employeeRoleId = employeeRoleId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
