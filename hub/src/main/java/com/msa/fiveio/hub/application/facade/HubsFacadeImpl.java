package com.msa.fiveio.hub.application.facade;

import com.msa.fiveio.hub.application.usecase.HubRouteService;
import com.msa.fiveio.hub.application.usecase.HubsService;
import com.msa.fiveio.hub.model.entity.Hubs;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsResponseDto;
import com.msa.fiveio.hub.presentation.dto.hubs.SearchResponseDto;
import com.msa.fiveio.hub.presentation.mapper.HubsMapper;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
@RequiredArgsConstructor
public class HubsFacadeImpl implements HubsFacade {

    private final HubsService hubsService;
    private final HubRouteService hubRouteService;

    @Async
    @Override
    public CompletableFuture<HubsResponseDto> createHubs(HubsRequestDto hubsRequestDto) {
        //허브 생성
        HubsResponseDto hubsResponseDto = hubsService.createHubs(hubsRequestDto);

        //허브 간 경로 생성
        //1. 허브 전체 리스트 조회
        List<Hubs> hubsList = hubsService.getHubList();

        //2. 허브간 경로 생성 (출발 ,도착 바꿔서)
        for (Hubs hubs : hubsList) {
            if (!hubs.getId().equals(hubsResponseDto.id())) {
                HubsResponseDto existingHubDto = HubsResponseDto.of(hubs);
                hubRouteService.createHubRoute(hubsResponseDto, existingHubDto);
                hubRouteService.createHubRoute(existingHubDto, hubsResponseDto);
            }
        }

        return CompletableFuture.completedFuture(hubsResponseDto);
    }

    @Override
    public HubsResponseDto readHubs(UUID id) {
        return hubsService.readHubs(id);
    }

    @Override
    public HubsResponseDto updateHubs(UUID id, HubsRequestDto hubsDto) {
        HubsResponseDto dto = hubsService.readHubs(id);
        Hubs hub = HubsMapper.hubsResponseDtoToEntity(dto);
        return hubsService.updateHubs(id, hubsDto, hub);
    }

    @Override
    public Page<SearchResponseDto> searchHubs(HubsRequestDto hubsDto, Pageable pageable) {
        return hubsService.searchHubs(hubsDto, pageable);
    }


}
