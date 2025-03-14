package com.msa.fiveio.hub.presentation.dto;


public record HubsRequestDto( String hubName,
                              String address,
                              Double latitude,
                              Double longitude)
{

}
