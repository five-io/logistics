package com.msa.fiveio.hub.application.facade;

import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteRequestDto;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteResponseDto;
import java.util.List;
import java.util.UUID;

public interface HubRouteFacade {

    HubRouteResponseDto createHubRoute(HubRouteRequestDto hubsDto);

    List<HubRouteResponseDto> getHubRouteList(UUID arriveHubId, UUID departHubId);
}
