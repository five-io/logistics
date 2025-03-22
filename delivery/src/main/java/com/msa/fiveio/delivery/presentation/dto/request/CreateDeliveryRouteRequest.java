package com.msa.fiveio.delivery.presentation.dto.request;

import com.msa.fiveio.delivery.infrastructure.client.dto.response.RouteResponseDto;
import com.msa.fiveio.delivery.presentation.dto.response.DeliveryResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateDeliveryRouteRequest {

    private DeliveryResponseDto delivery;  //배송정보
    private Long deliveryManager;          //배달원 Id
    private List<RouteResponseDto> routes;  //허브 이동정보 경로

}
