package com.msa.fiveio.ai.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AisCreateRequestDto {

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
	private String sendStatus;

	@Builder
	public AisCreateRequestDto(String departHubName, String transitPoint, String arriveHubName,
		String deliveryAddress, String recipientName, String recipientSlackId,
		String companyDeliveryManager, String productName, Long productQuantity,
		String requestNotes, String sendStatus) {
		this.departHubName = departHubName;
		this.transitPoint = transitPoint;
		this.arriveHubName = arriveHubName;
		this.deliveryAddress = deliveryAddress;
		this.recipientName = recipientName;
		this.recipientSlackId = recipientSlackId;
		this.companyDeliveryManager = companyDeliveryManager;
		this.productName = productName;
		this.productQuantity = productQuantity;
		this.requestNotes = requestNotes;
		this.sendStatus = sendStatus;
	}
}
