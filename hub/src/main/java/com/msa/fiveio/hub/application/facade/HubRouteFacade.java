package com.msa.fiveio.hub.application.facade;

import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteRequestDto;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteResponseDto;

public interface HubRouteFacade {

    HubRouteResponseDto createHubRoute(HubRouteRequestDto hubsDto);


}
