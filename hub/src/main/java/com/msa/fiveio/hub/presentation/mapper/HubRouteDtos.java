package com.msa.fiveio.hub.presentation.mapper;

import com.msa.fiveio.hub.model.entity.HubRoute;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteResponseDto;
import java.util.List;
import lombok.Getter;

@Getter
public class HubRouteDtos {

    private final List<HubRouteResponseDto> responseDtos;

    public HubRouteDtos(List<HubRouteResponseDto> responseDtos) {
        this.responseDtos = responseDtos;
    }

    public static HubRouteDtos fromList(List<HubRoute> hubsPage) {
        List<HubRouteResponseDto> dtoList = hubsPage.stream()
            .map(HubsMapper::entityToHubRouteResponseDto)
            .toList();

        return new HubRouteDtos(dtoList);
    }


}
