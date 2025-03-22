package com.msa.fiveio.product.presentation.dto;

import com.msa.fiveio.product.model.entity.ProductType;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderProductGetResponseDto {

    private String deliveryAddress;
    private UUID requesterCompanyId;
    private UUID departHubId;
    private UUID arriveHubId;
    private String productName;
    private double productPrice;
    private ProductType productType;
    private Boolean isOrderable;
}
