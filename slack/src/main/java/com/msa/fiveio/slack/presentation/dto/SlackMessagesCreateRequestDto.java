package com.msa.fiveio.slack.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SlackMessagesCreateRequestDto {

	@JsonProperty("user-id")
	private Long userId;

	@JsonProperty("receive-id")
	private UUID receiveId;

	@JsonProperty("order-id")
	private UUID orderId;

	private String message;

}
