package com.msa.fiveio.user.model.entity;


import com.msa.fiveio.user.model.entity.enums.ManagersTypeEnum;

public class DeliveryManagersFactory {

  public static DeliveryManagers createDeliveryManager(Users users, Integer sequence,
      ManagersTypeEnum managersType) {
    return DeliveryManagers.builder()
        .userId(users.getUserId())
        .hubId(users.getHubId())
        .managersType(managersType)
        .isWorking(false)
        .sequence(sequence)
        .build();
  }
}