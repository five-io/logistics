package com.msa.fiveio.hub.application.facade;

import com.msa.fiveio.hub.application.usecase.HubRouteService;
import com.msa.fiveio.hub.application.usecase.HubsService;
import com.msa.fiveio.hub.presentation.dto.RegionMap;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteRequestDto;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteResponseDto;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsResponseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HubRouteFacadeImpl implements HubRouteFacade {

    private final HubRouteService hubRouteService;
    private final HubsFacade hubsFacade;
    private final HubsService hubsService;

    @Override
    public HubRouteResponseDto createHubRoute(HubRouteRequestDto hubsDto) {
        //출발
        HubsResponseDto origin = hubsFacade.readHubs(hubsDto.getArriveHubId());
        //도착
        HubsResponseDto destination = hubsFacade.readHubs(hubsDto.getDepartHubId());

        return hubRouteService.createHubRoute(origin, destination);
    }

    @Override
    public List<HubRouteResponseDto> getHubRouteList(HubRouteRequestDto hubsDto) {

        List<HubRouteResponseDto> hubRouteResponseDtos = new ArrayList<>();

        RegionMap graph = new RegionMap();

        //중앙id (출발지)
        String[] main = {"경기 남부", "대전", "대구", "경상북도"};
        for (String name : main) {
            HubsResponseDto hub = hubsService.getHubByName(name);
            List<HubRouteResponseDto> result = hubRouteService.getHubsListByHubId(hub.id());

            for (HubRouteResponseDto dto : result) {
                graph.addRoute(hub.id(), dto.arriveId(), dto.distance());
            }
        }

        List<UUID> path = hubRouteService.findShortestRoute(hubsDto.getDepartHubId(),
            hubsDto.getArriveHubId(), graph);

        for (int i = 0; i < path.size() - 1; i++) {
            //출발,도착id로 검색
            HubRouteResponseDto responseDto = hubRouteService.getHubRouteByIds(path.get(i),
                path.get(i + 1));
            hubRouteResponseDtos.add(responseDto);
        }

        return hubRouteResponseDtos;
    }


}
