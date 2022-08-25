package com.umutates.izinmodule.repository;


import com.umutates.izinmodule.model.LeavesCalculationRuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LeavesCalculationRuleRepository extends JpaRepository<LeavesCalculationRuleEntity, Long> {


    //TODO:: bu sorgu 10 yıllık çalısan için doğru çalısmayabilir 10 hem kurala hem 2. kurala giriyor bu kuralı sorguyla değil methodla işletmek gerekebilir.
    @Query(" select lcr.leavesValue from LeavesCalculationRuleEntity lcr" +
            " where lcr.min <= :workingYear and lcr.max >= :workingYear")
    Integer findLeavesValueByBetweenMaxAndMin(@Param("workingYear") Integer workingYear);
}
