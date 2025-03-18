package com.msa.fiveio.delivery.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryCreateRequestDto {

    @JsonProperty("order-id")
    private final UUID orderId;

    @JsonProperty("depart-hub-id")
    private final UUID departHubId;

    @JsonProperty("arrive-hub-id")
    private final UUID arriveHubId;

    @JsonProperty("delivery-address")
    private final String deliveryAddress;

    @JsonProperty("recipient-name")
    private final String recipientName;

    // 전화번호로 대체
    @JsonProperty("recipient-slack-id")
    private final String recipientSlackId;
}
