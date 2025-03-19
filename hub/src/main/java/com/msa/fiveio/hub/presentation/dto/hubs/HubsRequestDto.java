package com.msa.fiveio.hub.presentation.dto.hubs;


public record HubsRequestDto(String hubName,
                             String address,
                             Double latitude,
                             Double longitude) {

}
