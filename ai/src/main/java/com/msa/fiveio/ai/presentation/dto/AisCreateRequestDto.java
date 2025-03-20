package com.msa.fiveio.ai.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AisCreateRequestDto {

	@JsonProperty("transit-point")
	private String transitPoint;

	@JsonProperty("delivery-status")
	private String deliveryStatus;

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
	private String productQuantity;

	@JsonProperty("request-notes")
	private String requestNotes;

	@Builder
	public AisCreateRequestDto(String transitPoint, String deliveryStatus, String deliveryAddress,
		String recipientName, String recipientSlackId, String companyDeliveryManager,
		String productName, String productQuantity, String requestNotes) {
		this.transitPoint = transitPoint;
		this.deliveryStatus = deliveryStatus;
		this.deliveryAddress = deliveryAddress;
		this.recipientName = recipientName;
		this.recipientSlackId = recipientSlackId;
		this.companyDeliveryManager = companyDeliveryManager;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.requestNotes = requestNotes;
	}
}
