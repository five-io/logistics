package com.msa.fiveio.hub.application.facade;

import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteRequestDto;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteResponseDto;
import java.util.List;

public interface HubRouteFacade {

    HubRouteResponseDto createHubRoute(HubRouteRequestDto hubsDto);

    List<HubRouteResponseDto> getHubRouteList(HubRouteRequestDto hubsDto);
}
