package com.msa.fiveio.delivery.application.usecase;

import com.msa.fiveio.delivery.infrastructure.client.HubClient;
import com.msa.fiveio.delivery.infrastructure.client.SlackClient;
import com.msa.fiveio.delivery.infrastructure.client.dto.request.SlacksCreateRequestDto;
import com.msa.fiveio.delivery.infrastructure.client.dto.response.HubsResponseDto;
import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalServiceImpl implements ExternalService {

    private final SlackClient slackClient;
    private final HubClient hubClient;

    @Override
    public void sendSlackRequest(DeliveryCreateRequestDto deliveryCreateRequestDto) {
        try {
            HubsResponseDto departHub = hubClient.readHubs(
                    deliveryCreateRequestDto.getDepartHubId())
                .getBody();
            HubsResponseDto arriveHub = hubClient.readHubs(
                    deliveryCreateRequestDto.getArriveHubId())
                .getBody();
            log.info("hub response: departHub = {}, arriveHub = {}", departHub.getHubName(),
                arriveHub.getHubName());

            SlacksCreateRequestDto request = SlacksCreateRequestDto.builder()
                .deliveryCreateRequestDto(deliveryCreateRequestDto)
                .departHub(departHub)
                .arriveHub(arriveHub)
                .build();

            slackClient.createSlack(request);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to send slack request", e);
        }
    }
}
