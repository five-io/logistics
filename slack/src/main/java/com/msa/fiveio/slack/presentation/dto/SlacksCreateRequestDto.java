package com.msa.fiveio.slack.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SlacksCreateRequestDto {

	@JsonProperty("receiver-company-id")
	private UUID receiverCompanyId;

	@JsonProperty("order-id")
	private UUID orderId;

	@JsonProperty("depart-id")
	private UUID departId;

	@JsonProperty("transit-point")
	private String transitPoint;

	@JsonProperty("arrive-id")
	private UUID arriveId;

	@JsonProperty("delivery-status")
	private String deliveryStatus;

	@JsonProperty("delivery-address")
	private String deliveryAddress;

	@JsonProperty("product-id")
	private UUID productId;

	@JsonProperty("recipient-name")
	private String recipientName;

	@JsonProperty("recipient-slack-id")
	private String recipientSlackId;

	@JsonProperty("company-delivery-manager")
	private String companyDeliveryManager;

	@JsonProperty("product-name")
	private String productName;

	@JsonProperty("product-quantity")
	private String productQuantity;

	@JsonProperty("request-notes")
	private String requestNotes;

	private String message;

}
