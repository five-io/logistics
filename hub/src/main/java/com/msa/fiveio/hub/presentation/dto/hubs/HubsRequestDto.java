package com.msa.fiveio.hub.presentation.dto.hubs;


public record HubsRequestDto(String hubName,
                             String address,
                             Double latitude,
                             Double longitude) {

    public HubsRequestDto(String hubName, String address) {
        this(hubName, address, null, null);
    }
}
