package com.umutates.izinmodule.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class RoleEntity {

    @Id
    private Integer roleId;
    private String roleName;
    private String roleShortCode;
    private Date createDate;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleShortCode() {
        return roleShortCode;
    }

    public void setRoleShortCode(String roleShortCode) {
        this.roleShortCode = roleShortCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date cdate) {
        this.createDate = cdate;
    }
}
