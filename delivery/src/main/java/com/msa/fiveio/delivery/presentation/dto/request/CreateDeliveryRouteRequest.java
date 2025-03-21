package com.msa.fiveio.delivery.presentation.dto.request;

import com.msa.fiveio.delivery.infrastructure.client.dto.RouteResponseDto;
import com.msa.fiveio.delivery.presentation.dto.response.DeliveryResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateDeliveryRouteRequest {

    private DeliveryResponseDto delivery;
    private Long deliveryManager;
    private List<RouteResponseDto> routes;

}
