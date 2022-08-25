package com.umutates.izinmodule.base;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GeneralEnumeration {


    public enum LeaveRequestStatus {
        ONAY_BEKLIYOR("ONAY_BEKLIYOR", "Yönetici Onayi Bekleniyor"),
        REDDEDILDI("REDDEDILDI", "İzin Talebi Yönetici Tarafından Reddedildi"),
        ONAYLANDI("ONAYLANDI", "İzin Talebiniz Onaylandi");

        private String shortCode;

        public String getShortCode() {
            return shortCode;
        }

        public void setShortCode(String shortCode) {
            this.shortCode = shortCode;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        private String description;

        LeaveRequestStatus(String shortCode, String description) {
            this.shortCode = shortCode;
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }

        public static List<LeaveRequestStatus> leaveRequestStatusList() {
            return Arrays.asList(LeaveRequestStatus.ONAY_BEKLIYOR,
                    LeaveRequestStatus.REDDEDILDI,
                    LeaveRequestStatus.ONAYLANDI);
        }

        public static LeaveRequestStatus findByShortCode(String shortCode){
            return leaveRequestStatusList().stream()
                    .filter(status -> status.toString().equalsIgnoreCase(shortCode))
                    .findFirst()
                    .orElse(null);
        }
    }


    public enum AvanceLeaveAmount {
        WORKING_FOR_1_YEAR(-5);

        private int amount;

        AvanceLeaveAmount(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return this.amount;
        }
    }

    public enum LeavesCalculationRuleConfig {
        ONE_TO_FIVE(1, "Bir yıldan başlayarak 5 yıl dahil", 1, 5, 15),
        FIVE_TO_TEN(2, "Beş yıldan fazla ve 10 yıl dahil", 5, 10, 18),
        TEN_TO_PLUS(3, "On yıldan fazla olanlara", 10, 100, 24);

        private int id;
        private String description;
        private int min;
        private int max;
        private int leaveAmount;

        LeavesCalculationRuleConfig(int id, String description, int min, int max, int leaveAmount ){
            this.id = id;
            this.description = description;
            this.min = min;
            this.max = max;
            this.leaveAmount = leaveAmount;
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }

        public int getLeaveAmount() {
            return leaveAmount;
        }

        public static List<LeavesCalculationRuleConfig> LeavesCalculationRuleConfigList() {
            return Arrays.asList(LeavesCalculationRuleConfig.ONE_TO_FIVE,
                    LeavesCalculationRuleConfig.FIVE_TO_TEN,
                    LeavesCalculationRuleConfig.TEN_TO_PLUS);
        }
    }

    public enum Roles {
        ADMIN(1, "ADMIN"),
        EMPLOYEE(2, "EMPLOYEE");

        private int roleId;
        private String roleShortCode;

        Roles(int roleId, String role){
            this.roleId = roleId;
            this.roleShortCode = role;
        }

        public int getRoleId() {
            return roleId;
        }

        public String getRoleShortCode() {
            return roleShortCode;
        }

        public static List<Roles> roleList() {
            return Arrays.asList(Roles.ADMIN,
                    Roles.EMPLOYEE);
        }

        public static List<Roles> findRoleByShortCodes(List<String> roleShortCodes){
            return roleList().stream()
                    .filter(role -> roleShortCodes.contains(role.getRoleShortCode()))
                    .collect(Collectors.toList());
        }

    }
}
