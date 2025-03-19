package com.msa.fiveio.hub.presentation.dto.hubRoutes;

import java.util.UUID;
import lombok.Getter;

@Getter
public class HubRouteRequestDto {

    private UUID arriveHubId;
    private UUID departHubId;

}
