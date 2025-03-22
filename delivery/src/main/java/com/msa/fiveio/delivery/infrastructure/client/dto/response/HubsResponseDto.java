package com.msa.fiveio.delivery.infrastructure.client.dto.response;

import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HubsResponseDto {

    private final UUID id;
    private final String hubName;
    private final String address;
    private final Double latitude;
    private final Double longitude;
}
