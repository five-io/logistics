package com.msa.fiveio.hub.application.usecase;

import com.msa.fiveio.hub.presentation.dto.RegionMap;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteResponseDto;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsResponseDto;
import java.util.List;
import java.util.UUID;

public interface HubRouteService {

    HubRouteResponseDto createHubRoute(HubsResponseDto start, HubsResponseDto end);

    List<HubRouteResponseDto> getHubsListByHubId(UUID id);

    List<UUID> findShortestRoute(UUID start, UUID end, RegionMap graph);

    HubRouteResponseDto getHubRouteByIds(UUID start, UUID end);
}
