package com.msa.fiveio.delivery.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.fiveio.delivery.model.entity.enums.DeliveryStatus;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliverySearchRequestDto {

    @JsonProperty("delivery-status")
    private final DeliveryStatus deliveryStatus;
    @JsonProperty("depart-hub-id")
    private final UUID departHubId;
    @JsonProperty("arrive-hub-id")
    private final UUID arriveHubId;
    @JsonProperty("delivery-address")
    private final String deliveryAddress;
    @JsonProperty("recipient-name")
    private final String recipientName;
    @JsonProperty("recipient-slack-id")
    private final String recipientSlackId;
    @JsonProperty("company-delivery-manager")
    private final Long companyDeliveryManager;
    @JsonProperty("order-id")
    private final UUID orderId;
}