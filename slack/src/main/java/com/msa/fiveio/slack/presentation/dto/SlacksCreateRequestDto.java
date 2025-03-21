package com.msa.fiveio.slack.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.fiveio.slack.model.entity.SendStatus;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SlacksCreateRequestDto {

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
