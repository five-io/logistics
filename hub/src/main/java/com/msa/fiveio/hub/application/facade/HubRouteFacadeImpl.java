package com.msa.fiveio.hub.application.facade;

import com.msa.fiveio.hub.application.usecase.HubRouteService;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteRequestDto;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteResponseDto;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HubRouteFacadeImpl implements HubRouteFacade {

    private final HubRouteService hubRouteService;
    private final HubsFacade hubsFacade;

    @Override
    public HubRouteResponseDto createHubRoute(HubRouteRequestDto hubsDto) {
        //출발
        HubsResponseDto origin = hubsFacade.readHubs(hubsDto.getArriveHubId());
        //도착
        HubsResponseDto destination = hubsFacade.readHubs(hubsDto.getDepartHubId());

        return hubRouteService.createHubRoute(origin, destination, hubsDto);
    }

}
