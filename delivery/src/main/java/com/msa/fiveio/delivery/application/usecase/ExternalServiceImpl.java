package com.msa.fiveio.delivery.application.usecase;

import com.msa.fiveio.delivery.infrastructure.client.HubClient;
import com.msa.fiveio.delivery.infrastructure.client.SlackClient;
import com.msa.fiveio.delivery.infrastructure.client.dto.request.SlacksCreateRequestDto;
import com.msa.fiveio.delivery.infrastructure.client.dto.response.HubsResponseDto;
import com.msa.fiveio.delivery.infrastructure.client.dto.response.RouteResponseDto;
import com.msa.fiveio.delivery.infrastructure.client.dto.response.UserResponseDto;
import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalServiceImpl implements ExternalService {

    private final SlackClient slackClient;
    private final HubClient hubClient;
//    private final UserClient userClient;

    @Override
    public void sendSlackRequest(DeliveryCreateRequestDto deliveryCreateRequestDto,
        String wayPoints, String companyDeliveryManagerName) {
        try {
            HubsResponseDto departHub = getHubResponseDto(
                deliveryCreateRequestDto.getDepartHubId());
            HubsResponseDto arriveHub = getHubResponseDto(
                deliveryCreateRequestDto.getArriveHubId());
            log.info("hub response: departHub = {}, arriveHub = {}", departHub.getHubName(),
                arriveHub.getHubName());

            SlacksCreateRequestDto request = SlacksCreateRequestDto.builder()
                .deliveryCreateRequestDto(deliveryCreateRequestDto)
                .departHub(departHub)
                .arriveHub(arriveHub)
                .wayPoints(wayPoints)
                .deliveryManagerName(companyDeliveryManagerName)
                .build();

            slackClient.createSlack(request);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to send slack request", e);
        }
    }

    @Override
    public List<RouteResponseDto> getHubRouteList(UUID arriveHubId, UUID departHubId) {
//        RouteRequestDto requestDto = RouteRequestDto.builder()
//            .arriveHubId(arriveHubId)
//            .departHubId(departHubId)
//            .build();
//        return hubClient.getHubRouteList(requestDto);
        return hubClient.getHubRouteList(arriveHubId, departHubId);
    }

    @Override
    public UserResponseDto getDeliveryManager(UUID hubID, String type) {
//        return userClient.getDeliveryManagerId(hubID, type);
        return UserResponseDto.builder()
            .id(1L)
            .userName("짱구")
            .build();
    }

    @Override
    public String getWayPoints(List<UUID> wayPointIds) {
        return wayPointIds.stream()
            .map(this::getHubResponseDto)
            .map(HubsResponseDto::getHubName)
            .collect(Collectors.joining(","));
    }

    private HubsResponseDto getHubResponseDto(UUID hubId) {
        return hubClient.readHubs(hubId).getBody();
    }
}
