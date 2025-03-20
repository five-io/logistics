package com.msa.fiveio.delivery.presentation.dto.response;

import com.msa.fiveio.delivery.model.entity.Recipient;
import com.msa.fiveio.delivery.model.entity.enums.DeliveryStatus;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryResponseDto {

    private final UUID deliveryId;
    private final UUID orderId;
    private final DeliveryStatus deliveryStatus;
    private final UUID departHubId;
    private final UUID arriveHubId;
    private final String deliveryAddress;
    private final Long companyDeliveryManagerId;
    private final Recipient recipient;
}
