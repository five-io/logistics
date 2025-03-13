package com.msa.fiveio.hub.presentation.dto;

import com.msa.fiveio.hub.model.entity.Hubs;
import java.util.UUID;
import lombok.Getter;


public record HubsResponseDto (UUID id,
                               String hub_name,
                               String address,
                               Double latitude,
                               Double longitude)
{

    public static HubsResponseDto of(Hubs hubs) {
        return new HubsResponseDto(hubs.getId(), hubs.getHubName(), hubs.getAddress(), hubs.getLatitude(), hubs.getLongitude());
    }
}
