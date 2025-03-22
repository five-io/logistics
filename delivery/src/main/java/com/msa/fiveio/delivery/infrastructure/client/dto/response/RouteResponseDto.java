package com.msa.fiveio.delivery.infrastructure.client.dto.response;


import java.util.UUID;
import lombok.Builder;

public record RouteResponseDto(UUID departId,
                               UUID arriveId,
                               Long takenTime,
                               Long distance
) {

    @Builder
    public RouteResponseDto(UUID departId, UUID arriveId, Long takenTime, Long distance) {
        this.departId = departId;
        this.arriveId = arriveId;
        this.takenTime = takenTime;
        this.distance = distance / 1000; //km변경
    }


}
