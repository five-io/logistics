package com.msa.fiveio.slack.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SlacksReadResponseDto {

	@JsonProperty("total-contents")
	private Long totalContents;

	private Integer size;

	@JsonProperty("current-page")
	private Integer currentPage;

	@JsonProperty("slack-list")
	private List<SlacksDto> slackList;

	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class SlacksDto {

		@JsonProperty("slack-id")
		private UUID slackId;

		@JsonProperty("receive-id")
		private UUID receiveId;

		@JsonProperty("order-id")
		private UUID orderId;

		private String message;

		@JsonProperty("delivery-time")
		private LocalDateTime deliveryTime;
	}
}
