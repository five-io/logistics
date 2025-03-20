package com.msa.fiveio.hub.presentation.dto.hubs;

import com.msa.fiveio.hub.model.entity.Hubs;
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

    public static HubsResponseDto of(Hubs hubs) {
        return new HubsResponseDto(hubs.getId(), hubs.getHubName(), hubs.getAddress(),
            hubs.getLatitude(), hubs.getLongitude());
    }


}
