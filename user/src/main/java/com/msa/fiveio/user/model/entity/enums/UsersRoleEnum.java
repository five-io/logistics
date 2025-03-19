package com.msa.fiveio.user.model.entity.enums;

import lombok.Getter;

@Getter
public enum UsersRoleEnum {
  ROLE_MASTER(Authority.MASTER),
  ROLE_HUB_MANAGER(Authority.HUB_MANAGER),
  ROLE_DELIVERY_MANAGER(Authority.DELIVERY_MANAGER),
  ROLE_COMPANY_MANAGER(Authority.COMPANY_MANAGER);

  private final String authority;

  UsersRoleEnum(String authority) {
    this.authority = authority;
  }

  public static class Authority {
    public static final String COMPANY_MANAGER = "ROLE_COMPANY_MANAGER";
    public static final String DELIVERY_MANAGER = "ROLE_DELIVERY_MANAGER";
    public static final String HUB_MANAGER = "ROLE_HUB_MANAGER";
    public static final String MASTER = "ROLE_MASTER";

  }
}