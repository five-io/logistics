package com.msa.fiveio.hub.application.usecase;

import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteResponseDto;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsResponseDto;

public interface HubRouteService {

    HubRouteResponseDto createHubRoute(HubsResponseDto start, HubsResponseDto end);

}
