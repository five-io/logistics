package com.msa.fiveio.hub.presentation.mapper;

import com.msa.fiveio.hub.model.entity.HubRoute;
import com.msa.fiveio.hub.model.entity.Hubs;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteResponseDto;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsResponseDto;
import com.msa.fiveio.hub.presentation.dto.hubs.SearchResponseDto;

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

    public static HubsResponseDto entityToAllHubDtos(Hubs hubs) {
        return HubsResponseDto.builder()
            .id(hubs.getId())
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


    public static HubRouteResponseDto entityToHubRouteResponseDto(HubRoute hubRoute) {
        return HubRouteResponseDto.builder()
            .departId(hubRoute.getDepartId())
            .arriveId(hubRoute.getArriveId())
            .takenTime(hubRoute.getTakenTime())
            .distance(hubRoute.getDistance())
            .build();
    }
}
