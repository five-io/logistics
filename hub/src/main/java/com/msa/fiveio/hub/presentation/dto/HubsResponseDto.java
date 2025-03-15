package com.msa.fiveio.hub.presentation.dto;

import com.msa.fiveio.hub.model.entity.Hubs;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;


public record HubsResponseDto (UUID id,
                               String hubName,
                               String address,
                               Double latitude,
                               Double longitude)
{

    @Builder
    public HubsResponseDto (UUID id, String hubName, String address, Double latitude, Double longitude) {
        this.id = id;
        this.hubName = hubName;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
