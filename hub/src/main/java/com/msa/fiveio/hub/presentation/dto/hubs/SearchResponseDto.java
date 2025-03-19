package com.msa.fiveio.hub.presentation.dto.hubs;


import lombok.Builder;


public record SearchResponseDto(String hubName,
                                String address,
                                Double latitude,
                                Double longitude) {

    @Builder
    public SearchResponseDto(String hubName, String address, Double latitude, Double longitude) {
        this.hubName = hubName;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
