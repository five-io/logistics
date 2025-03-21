package com.msa.fiveio.product.presentation.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductCreateResponseDto {

    private UUID productId;
    private String productName;
    private String productDetail;
    private UUID hubId;
    private UUID companyId;

}
