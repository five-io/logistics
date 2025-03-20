package com.msa.fiveio.slack.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SlacksUpdateResponseDto {

	@JsonProperty("receive-id")
	private UUID receiveId;

	@JsonProperty("order-id")
	private UUID orderId;

	private String message;

	@JsonProperty("delivery-time")
	private LocalDateTime deliveryTime;
}
