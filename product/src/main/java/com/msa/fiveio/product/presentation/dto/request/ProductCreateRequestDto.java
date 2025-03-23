package com.msa.fiveio.product.presentation.dto.request;

import java.util.UUID;
import lombok.Getter;

@Getter
public class ProductCreateRequestDto {

    private String productName;
    private String productDetail;
    private UUID companyId;
    private double productPrice;

}
