package com.msa.fiveio.order.infrastructure.messaging.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DeliveryCreateRequest {

    private final UUID orderId;
    private final UUID departHubId;
    private final UUID arriveHubId;
    private final String deliveryAddress;
    private final String recipientName;
    private final String recipientSlackId;

}
