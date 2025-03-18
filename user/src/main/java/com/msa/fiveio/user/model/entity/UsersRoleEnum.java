package com.msa.fiveio.user.model.entity;

import lombok.Getter;

@Getter
public enum UsersRoleEnum {
    ROLE_MASTER(Authrity.MASTER),
    ROLE_HUB_MANAGER(Authrity.HUB_MANAGER),
    ROLE_DELIVERY_MANAGER(Authrity.DELIVERY_MANAGER),
    ROLE_COMPANY_MANAGER(Authrity.COMPANY_MANAGER);

    private final String authority;

    UsersRoleEnum(String authority) {
        this.authority = authority;
    }

    public static class Authrity {
        public static final String COMPANY_MANAGER = "ROLE_COMPANY_MANAGER";
        public static final String DELIVERY_MANAGER = "ROLE_DELIVERY_MANAGER";
        public static final String HUB_MANAGER = "ROLE_HUB_MANAGER";
        public static final String MASTER = "ROLE_MASTER";

    }
}
