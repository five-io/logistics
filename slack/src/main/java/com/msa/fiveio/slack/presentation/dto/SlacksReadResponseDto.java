package com.msa.fiveio.slack.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.fiveio.slack.model.entity.SendStatus;
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

		@JsonProperty("order-id")
		private UUID orderId;

		@JsonProperty("depart-hub-name")
		private String departHubName;

		@JsonProperty("transit-point")
		private String transitPoint;

		@JsonProperty("arrive-hub-name")
		private String arriveHubName;

		@JsonProperty("delivery-address")
		private String deliveryAddress;

		@JsonProperty("recipient-name")
		private String recipientName;

		@JsonProperty("recipient-slack-id")
		private String recipientSlackId;

		@JsonProperty("company-delivery-manager")
		private String companyDeliveryManager;

		@JsonProperty("product-name")
		private String productName;

		@JsonProperty("product-quantity")
		private Long productQuantity;

		@JsonProperty("request-notes")
		private String requestNotes;

		@JsonProperty("send-status")
		private SendStatus sendStatus;

		private String message;
	}
}
