package com.msa.fiveio.product.presentation.dto.response;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductGetResponseDto {

    private UUID productId;
    private String productName;
    private String productDetail;
    private UUID hubId;
    private UUID companyId;
    private double productPrice;

    @Builder
    public ProductGetResponseDto(UUID productId, String productName, String productDetail,
            UUID hubId,
            UUID companyId, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDetail = productDetail;
        this.hubId = hubId;
        this.companyId = companyId;
        this.productPrice = productPrice;
    }
}
