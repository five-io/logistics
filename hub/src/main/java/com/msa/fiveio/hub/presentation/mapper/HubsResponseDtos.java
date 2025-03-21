package com.msa.fiveio.hub.presentation.mapper;

import com.msa.fiveio.hub.model.entity.Hubs;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsResponseDto;
import java.util.List;
import lombok.Getter;

@Getter
public class HubsResponseDtos {

    private final List<HubsResponseDto> responseDtos;

    public HubsResponseDtos(List<HubsResponseDto> responseDtos) {
        this.responseDtos = responseDtos;
    }

    public static HubsResponseDtos fromList(List<Hubs> hubsPage) {
        List<HubsResponseDto> dtoList = hubsPage.stream()
            .map(HubsMapper::entityToAllHubDtos)
            .toList();

        return new HubsResponseDtos(dtoList);
    }

}
