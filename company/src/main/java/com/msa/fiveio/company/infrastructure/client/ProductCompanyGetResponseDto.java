package com.msa.fiveio.company.infrastructure.client;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductCompanyGetResponseDto {

    private UUID arriveHubId;
    private String deliveryAddress;
}
