package com.msa.fiveio.slack.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.fiveio.slack.model.entity.SendStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SlacksUpdateRequestDto {

	@NotNull(message = "주문 ID는 필수 입력값입니다.")
	@JsonProperty("order-id")
	private UUID orderId;

	@NotNull(message = "발신 상태는 필수 입력값입니다.")
	@JsonProperty("send-status")
	private SendStatus sendStatus;
}
