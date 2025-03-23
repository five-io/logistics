package com.msa.fiveio.delivery.presentation.dto.request;

import java.util.UUID;

public record DeliveryRouteRequest(UUID deliveryId,
                                   UUID arrviedHubId) {

}
