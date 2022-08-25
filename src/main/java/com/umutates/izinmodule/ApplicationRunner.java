package com.umutates.izinmodule;

import com.umutates.izinmodule.base.GeneralEnumeration;
import com.umutates.izinmodule.model.LeavesCalculationRuleEntity;
import com.umutates.izinmodule.model.RoleEntity;
import com.umutates.izinmodule.repository.LeavesCalculationRuleRepository;
import com.umutates.izinmodule.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    LeavesCalculationRuleRepository leavesCalculationRuleRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        // to-fill conf table
        //TODO: zamanım olursa bu kısmı flyway'e taşıyabilirim.
        this.saveConfigDataToLeavesCalculationRule();
        this.saveRoleTableConfig();

    }

    private void saveConfigDataToLeavesCalculationRule(){
        GeneralEnumeration.LeavesCalculationRuleConfig.LeavesCalculationRuleConfigList().forEach(leavesCalculationRuleConfig -> {
            LeavesCalculationRuleEntity leavesCalculationRuleEntity = new LeavesCalculationRuleEntity();
            leavesCalculationRuleEntity.setLeavesCalculationRuleId(leavesCalculationRuleConfig.getId());
            leavesCalculationRuleEntity.setMin(leavesCalculationRuleConfig.getMin());
            leavesCalculationRuleEntity.setMax(leavesCalculationRuleConfig.getMax());
            leavesCalculationRuleEntity.setLeavesValue(leavesCalculationRuleConfig.getLeaveAmount());
            leavesCalculationRuleEntity.setDescription(leavesCalculationRuleConfig.getDescription());
            leavesCalculationRuleRepository.save(leavesCalculationRuleEntity);

        });
    }

    private void saveRoleTableConfig() {

        GeneralEnumeration.Roles.roleList().forEach(role -> {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setRoleId(role.getRoleId());
            roleEntity.setRoleName(role.getRoleShortCode());
            roleEntity.setRoleShortCode(role.getRoleShortCode());
            roleEntity.setCreateDate(new Date());
            roleRepository.save(roleEntity);
        });

    }
}
