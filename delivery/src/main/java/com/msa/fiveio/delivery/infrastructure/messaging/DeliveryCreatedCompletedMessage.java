package com.msa.fiveio.delivery.infrastructure.messaging;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DeliveryCreatedCompletedMessage {
    private final UUID orderId;
    private final UUID deliveryId;
}
