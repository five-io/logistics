package com.msa.fiveio.order.infrastructure.client.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyResponseDto {

    private final String deliveryAddress;
    private final UUID requesterCompanyId;
    private final UUID departHubId;
    private final UUID arriveHubId;

}
