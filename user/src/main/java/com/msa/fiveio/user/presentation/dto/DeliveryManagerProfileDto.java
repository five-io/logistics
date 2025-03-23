package com.msa.fiveio.user.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.fiveio.user.model.entity.DeliveryManagers;
import com.msa.fiveio.user.model.entity.enums.ManagersTypeEnum;
import java.util.UUID;
import lombok.Getter;

@Getter
public class DeliveryManagerProfileDto {

  private UUID hubId;
  private ManagersTypeEnum managersType;
  private String sequence;
  private Boolean isWorking;

  public DeliveryManagerProfileDto(DeliveryManagers deliveryManager) {
    this.hubId = deliveryManager.getHubId(); // ✅ 더 이상 String 변환 불필요
    this.managersType = deliveryManager.getManagersType();
    this.sequence = deliveryManager.getSequence();
    this.isWorking = deliveryManager.getIsWorking();
  }
}
