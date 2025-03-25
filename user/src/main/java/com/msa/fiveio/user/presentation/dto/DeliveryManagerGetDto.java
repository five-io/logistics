package com.msa.fiveio.user.presentation.dto;

import com.msa.fiveio.user.model.entity.DeliveryManagers;
import com.msa.fiveio.user.model.entity.enums.ManagersTypeEnum;
import java.util.UUID;
import lombok.Getter;

@Getter
public class DeliveryManagerGetDto {

  private UUID hubId;
  private String sequence;
  private Boolean isWorking;
  private ManagersTypeEnum managerType;

  public DeliveryManagerGetDto(DeliveryManagers deliveryManager) {
    this.hubId = deliveryManager.getHubId(); // ✅ 더 이상 String 변환 불필요
    this.managerType = deliveryManager.getManagersType();
    this.sequence = deliveryManager.getSequence();
    this.isWorking = deliveryManager.getIsWorking();
  }
}
