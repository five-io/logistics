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

	@Pattern(regexp = "SEND_SUCCESS|SEND_FAILURE|SEND_ERROR|SEND_TIMEOUT",
		message = "발신 상태는 SEND_SUCCESS, SEND_FAILURE, SEND_ERROR, SEND_TIMEOUT 만 허용합니다.")
	@NotNull(message = "발신 상태는 필수 입력값입니다.")
	@JsonProperty("send-status")
	private SendStatus sendStatus;
}
