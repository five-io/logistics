package com.msa.fiveio.hub.presentation.dto.hubs;

import java.util.UUID;
import lombok.Builder;


public record HubsResponseDto(UUID id,
                              String hubName,
                              String address,
                              Double latitude,
                              Double longitude) {

    @Builder
    public HubsResponseDto(UUID id, String hubName, String address, Double latitude,
        Double longitude) {
        this.id = id;
        this.hubName = hubName;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }


}
