package com.msa.fiveio.hub.application.facade;

import com.msa.fiveio.hub.application.usecase.HubRouteService;
import com.msa.fiveio.hub.application.usecase.HubsService;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsResponseDto;
import com.msa.fiveio.hub.presentation.dto.hubs.SearchResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
@RequiredArgsConstructor
public class HubsFacadeImpl implements HubsFacade {

    private final HubsService hubsService;
    private final HubRouteService hubRouteService;


    @Override
    public HubsResponseDto createHubs(HubsRequestDto hubsRequestDto) {
        HubsResponseDto hubsResponseDto = hubsService.createHubs(hubsRequestDto);
        return hubsResponseDto;
    }

    @Override
    public void createHubInit(HubsResponseDto start, HubsResponseDto end) {
        hubRouteService.createHubRoute(start, end);
        hubRouteService.createHubRoute(end, start);
    }

    @Override
    public HubsResponseDto readHubs(UUID id) {
        return hubsService.readHubs(id);
    }

    @Override
    public HubsResponseDto updateHubs(UUID id, HubsRequestDto hubsDto) {
        return hubsService.updateHubs(id, hubsDto);
    }

    @Override
    public Page<SearchResponseDto> searchHubs(HubsRequestDto hubsDto, Pageable pageable) {
        return hubsService.searchHubs(hubsDto, pageable);
    }


}
