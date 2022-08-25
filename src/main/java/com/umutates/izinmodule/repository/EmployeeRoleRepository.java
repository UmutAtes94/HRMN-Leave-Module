package com.umutates.izinmodule.repository;

import com.umutates.izinmodule.model.EmployeeRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRoleRepository extends JpaRepository<EmployeeRoleEntity, Long> {

    List<EmployeeRoleEntity> findByEmployeeId(Long employeeId);
}
