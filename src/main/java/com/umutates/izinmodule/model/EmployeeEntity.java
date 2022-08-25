package com.umutates.izinmodule.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;
    private String name;
    private String surname;
    private LocalDate startDate;
    private Integer numberOfLeaves;


    @OneToMany(fetch = FetchType.LAZY, targetEntity = EmployeeRoleEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "emp", referencedColumnName = "employeeId")
    private List<EmployeeRoleEntity> employeeRoleEntities;

    public List<EmployeeRoleEntity> getEmployeeRoleEntities() {
        return employeeRoleEntities;
    }

    public void setEmployeeRoleEntities(List<EmployeeRoleEntity> employeeRoleEntities) {
        this.employeeRoleEntities = employeeRoleEntities;
    }

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

    public Integer getNumberOfLeaves() {
        return numberOfLeaves;
    }

    public void setNumberOfLeaves(Integer numberOfLeaves) {
        this.numberOfLeaves = numberOfLeaves;
    }
}
