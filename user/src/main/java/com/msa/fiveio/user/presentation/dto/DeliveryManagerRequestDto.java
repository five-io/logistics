package com.msa.fiveio.user.presentation.dto;

import com.msa.fiveio.user.model.entity.enums.ManagersTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeliveryManagerRequestDto {

  @NotNull(message = "배송담당자 id는 필수입니다.")
  private Long userId;

  @NotNull(message = "배송담당자 타입은 필수입니다.")
  private ManagersTypeEnum managersType;
}
