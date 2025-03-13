package com.msa.fiveio.hub.presentation.dto;


public record HubsRequestDto( String hub_name,
                              String address,
                              Double latitude,
                              Double longitude)
{

}
