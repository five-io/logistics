package com.msa.fiveio.hub.presentation.dto.hubRoutes;


import com.msa.fiveio.hub.model.entity.HubRoute;
import java.util.UUID;
import lombok.Builder;


public record HubRouteResponseDto(UUID departId,
                                  UUID arriveId,
                                  Long takenTime,
                                  Long distance
) {

    @Builder
    public HubRouteResponseDto(UUID departId, UUID arriveId, Long takenTime, Long distance) {
        this.departId = departId;
        this.arriveId = arriveId;
        this.takenTime = takenTime;
        this.distance = distance;
    }

    public static HubRouteResponseDto of(HubRoute savedRoute) {
        return new HubRouteResponseDto(
            savedRoute.getDepartId(),
            savedRoute.getArriveId(),
            savedRoute.getTakenTime(),
            savedRoute.getDistance());
    }
}
