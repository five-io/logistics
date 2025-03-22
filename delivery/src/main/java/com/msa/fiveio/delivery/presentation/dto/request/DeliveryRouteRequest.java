package com.msa.fiveio.delivery.presentation.dto.request;

import com.msa.fiveio.delivery.model.entity.enums.DeliveryRouteStatus;
import java.util.UUID;

public record DeliveryRouteRequest(UUID deliveryId,
                                   UUID arrviedHubId,
                                   DeliveryRouteStatus status) {

}
