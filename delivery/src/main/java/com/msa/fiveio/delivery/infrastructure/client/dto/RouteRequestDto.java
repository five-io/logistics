package com.msa.fiveio.delivery.infrastructure.client.dto;


import java.util.UUID;
import lombok.Getter;

@Getter
public class RouteRequestDto {

    private UUID arriveHubId;
    private UUID departHubId;

}