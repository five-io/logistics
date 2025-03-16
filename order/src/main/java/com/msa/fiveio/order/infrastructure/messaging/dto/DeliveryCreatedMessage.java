package com.msa.fiveio.order.infrastructure.messaging.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryCreatedMessage {

    private final UUID orderId;
    private final UUID deliveryId;

}
