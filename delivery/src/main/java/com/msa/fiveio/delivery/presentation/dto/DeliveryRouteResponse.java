package com.msa.fiveio.delivery.presentation.dto;

import com.msa.fiveio.delivery.model.entity.Delivery;
import com.msa.fiveio.delivery.model.entity.DeliveryRoute;
import com.msa.fiveio.delivery.model.entity.DeliveryRouteDetails;
import com.msa.fiveio.delivery.model.entity.enums.DeliveryRouteStatus;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryRouteResponse {

    private UUID deliveryRouteId;
    private Delivery delivery;
    private DeliveryRouteDetails deliveryRouteDetails;
    private Double actualDistanceKm;
    private Long actualDurationMin;
    private DeliveryRouteStatus deliveryRouteStatus;
    private Long deliveryManager;

    // 변환 메서드
    public static DeliveryRouteResponse from(DeliveryRoute deliveryRoute) {
        return com.msa.fiveio.delivery.presentation.dto.DeliveryRouteResponse.builder()
            .delivery(deliveryRoute.getDelivery())
            .deliveryRouteDetails(deliveryRoute.getDeliveryRouteDetails())
            .actualDistanceKm(deliveryRoute.getActualDistanceKm())
            .actualDurationMin(deliveryRoute.getActualDurationMin())
            .deliveryRouteStatus(deliveryRoute.getDeliveryRouteStatus())
            .deliveryManager(deliveryRoute.getDeliveryManager())
            .build();
    }
}
