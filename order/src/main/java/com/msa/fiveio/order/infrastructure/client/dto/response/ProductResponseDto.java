package com.msa.fiveio.order.infrastructure.client.dto.response;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponseDto {

    private final String deliveryAddress;
    private final UUID requesterCompanyId;
    private final UUID departHubId;
    private final UUID arriveHubId;
    private final Double productPrice;
    private final String productName;

}
