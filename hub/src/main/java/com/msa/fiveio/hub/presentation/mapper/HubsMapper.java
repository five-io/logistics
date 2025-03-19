package com.msa.fiveio.hub.presentation.mapper;

import com.msa.fiveio.hub.model.entity.Hubs;
import com.msa.fiveio.hub.presentation.dto.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.HubsResponseDto;
import com.msa.fiveio.hub.presentation.dto.SearchResponseDto;

public class HubsMapper {


    public static HubsResponseDto entityToHubsResponseDto(Hubs hubs) {
        return HubsResponseDto.builder()
            .id(hubs.getId())
            .hubName(hubs.getHubName())
            .address(hubs.getAddress())
            .latitude(hubs.getLatitude())
            .longitude(hubs.getLongitude())
            .build();

    }

    public static SearchResponseDto entityToSearchResponseDto(Hubs hubs) {
        return SearchResponseDto.builder()
            .hubName(hubs.getHubName())
            .address(hubs.getAddress())
            .latitude(hubs.getLatitude())
            .longitude(hubs.getLongitude())
            .build();

    }

    public static Hubs hubsResponseDtoToEntity(HubsResponseDto hubs) {
        return Hubs.builder()
            .hubName(hubs.hubName())
            .address(hubs.address())
            .latitude(hubs.latitude())
            .longitude(hubs.longitude())
            .build();

    }

    public static Hubs hubsCreateRequestDtoToEntity(HubsRequestDto hubsRequestDto) {
        return Hubs.builder()
            .hubName(hubsRequestDto.hubName())
            .address(hubsRequestDto.address())
            .build();
    }


}
