package com.msa.fiveio.delivery.application.usecase;

import com.msa.fiveio.delivery.infrastructure.client.dto.response.RouteResponseDto;
import com.msa.fiveio.delivery.infrastructure.client.dto.response.UserResponseDto;
import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;
import java.util.List;
import java.util.UUID;

public interface ExternalService {

    void sendSlackRequest(DeliveryCreateRequestDto deliveryCreateRequestDto, String wayPoints, String companyDeliveryManager);

    List<RouteResponseDto> getHubRouteList(UUID arriveHubId, UUID departHubId);

    UserResponseDto getDeliveryManager(UUID hubID, String type);

    String getWayPoints(List<UUID> wayPointIds);
}
