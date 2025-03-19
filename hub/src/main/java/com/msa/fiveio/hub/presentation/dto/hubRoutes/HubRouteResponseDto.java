package com.msa.fiveio.hub.presentation.dto.hubRoutes;


import com.msa.fiveio.hub.model.entity.HubRoute;
import java.util.UUID;


public record HubRouteResponseDto(UUID depart_id,
                                  UUID arrive_id,
                                  Long taken_time,
                                  Long distance
) {


    public static HubRouteResponseDto of(HubRoute savedRoute) {
        return new HubRouteResponseDto(savedRoute.getArriveId(),
            savedRoute.getDepartId(),
            savedRoute.getTakenTime(),
            savedRoute.getDistance());
    }
}
