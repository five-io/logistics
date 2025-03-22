package com.msa.fiveio.slack.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.fiveio.slack.model.entity.SendStatus;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SlacksUpdateRequestDto {

	@JsonProperty("order-id")
	private UUID orderId;

	@JsonProperty("send-status")
	private SendStatus sendStatus;
}
