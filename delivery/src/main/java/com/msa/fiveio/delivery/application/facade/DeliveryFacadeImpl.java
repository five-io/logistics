package com.msa.fiveio.delivery.application.facade;

import com.msa.fiveio.delivery.application.usecase.DeliveryRouteService;
import com.msa.fiveio.delivery.application.usecase.DeliveryService;
import com.msa.fiveio.delivery.application.usecase.ExternalService;
import com.msa.fiveio.delivery.infrastructure.client.dto.response.RouteResponseDto;
import com.msa.fiveio.delivery.infrastructure.client.dto.response.UserResponseDto;
import com.msa.fiveio.delivery.presentation.dto.request.CreateDeliveryRouteRequest;
import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;
import com.msa.fiveio.delivery.presentation.dto.request.DeliverySearchRequestDto;
import com.msa.fiveio.delivery.presentation.dto.response.DeliveryResponseDto;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryFacadeImpl implements DeliveryFacade {

    private final DeliveryService deliveryService;
    private final ExternalService externalService;
    private final DeliveryRouteService deliveryRouteService;

    @Override
    public void createDelivery(DeliveryCreateRequestDto deliveryRequestDto) {
        UserResponseDto companyDeliveryManager = getDeliveryManager(deliveryRequestDto.getArriveHubId(), "COMPANY");
        UserResponseDto hubDeliveryManager = getDeliveryManager(deliveryRequestDto.getDepartHubId(), "HUB");

        DeliveryResponseDto deliveryResponseDto = deliveryService.createDelivery(deliveryRequestDto, companyDeliveryManager.getId());
        log.info("Created delivery: {}", deliveryResponseDto.getDeliveryId());
        List<RouteResponseDto> routeResponseDtos = externalService.getHubRouteList(deliveryRequestDto.getArriveHubId(), deliveryRequestDto.getDepartHubId());

        createDeliveryRoute(deliveryResponseDto, hubDeliveryManager.getId(), routeResponseDtos);

        List<UUID> waypoints = getWayPoints(routeResponseDtos);
        String waypointsString = externalService.getWayPoints(waypoints);

        sendSlackRequest(deliveryRequestDto, waypointsString, companyDeliveryManager.getUserName());
    }

    @Override
    public String updateStatus(UUID deliveryId, String status) {
        return deliveryService.updateStatus(deliveryId, status);
    }

    @Override
    public Page<DeliveryResponseDto> readDeliveries(DeliverySearchRequestDto requestDto,
        Pageable pageable) {
        return deliveryService.readDeliveries(requestDto, pageable);
    }

    @Override
    public DeliveryResponseDto readDelivery(UUID deliveryId) {
        return deliveryService.readDelivery(deliveryId);
    }

    @Override
    public String getDeliveryStatus(UUID orderId) {
        return deliveryService.getDeliveryStatus(orderId);
    }

    private UserResponseDto getDeliveryManager(UUID hubId, String type) {
        return externalService.getDeliveryManager(hubId, type);
    }

    private List<UUID> getWayPoints(List<RouteResponseDto> routeResponseDtos) {
        return routeResponseDtos.stream()
            .map(RouteResponseDto::arriveId)
            .toList();
    }

    private void createDeliveryRoute(
        DeliveryResponseDto deliveryResponseDto,
        Long hubDeliveryManagerId,
        List<RouteResponseDto> routeResponseDtos
    ) {
        deliveryRouteService.createDelRoute(
            new CreateDeliveryRouteRequest(deliveryResponseDto, hubDeliveryManagerId,
                routeResponseDtos));
    }

    private void sendSlackRequest(
        DeliveryCreateRequestDto deliveryRequestDto,
        String waypointsString,
        String companyDeliveryManagerName
    ) {
        externalService.sendSlackRequest(deliveryRequestDto, waypointsString,
            companyDeliveryManagerName);
    }
}
