package com.msa.fiveio.hub.application.usecase;

import com.msa.fiveio.hub.infrastructure.client.KakaoMobility;
import com.msa.fiveio.hub.model.entity.HubRoute;
import com.msa.fiveio.hub.model.repository.HubRouteRepository;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.DirectionsResponseDto;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteRequestDto;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteResponseDto;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubRouteServiceImpl implements HubRouteService {

    private final KakaoMobility kakaoMobilityService;
    private final HubRouteRepository hubRouteRepository;

    @Override
    public HubRouteResponseDto createHubRoute(HubsResponseDto start, HubsResponseDto end,
        HubRouteRequestDto hubsDto) {
        //출발
        String origin = start.longitude().toString() + "," + start.latitude().toString();
        //도착
        String destination = end.longitude().toString() + "," + end.latitude().toString();
        DirectionsResponseDto responseDto = kakaoMobilityService.distanceMatrix(origin,
            destination);
        HubRoute hubRoute = HubRoute.builder()
            .departId(hubsDto.getDepartHubId())
            .arriveId(hubsDto.getArriveHubId())
            .takenTime(Long.valueOf(responseDto.getRoutes()[0].getSummary().getDuration()))
            .distance(Long.valueOf(responseDto.getRoutes()[0].getSummary().getDistance()))
            .build();

        HubRoute savedRoute = hubRouteRepository.save(hubRoute);

        return HubRouteResponseDto.of(savedRoute);
    }
}
