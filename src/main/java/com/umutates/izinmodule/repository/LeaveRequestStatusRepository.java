package com.umutates.izinmodule.repository;

import com.umutates.izinmodule.model.LeavesRequestStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRequestStatusRepository extends JpaRepository<LeavesRequestStatusEntity, Long> {

    LeavesRequestStatusEntity findByEmployeeIdAndStatusShortCode(Long employeeId, String statusShortCode);
}
