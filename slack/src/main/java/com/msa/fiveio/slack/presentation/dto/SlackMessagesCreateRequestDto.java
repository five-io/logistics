package com.msa.fiveio.slack.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SlackMessagesCreateRequestDto {

	@JsonProperty("user-id")
	private String userId;

	@JsonProperty("receive-id")
	private String receiveId;

	@JsonProperty("order-id")
	private String orderId;

	private String message;

}
