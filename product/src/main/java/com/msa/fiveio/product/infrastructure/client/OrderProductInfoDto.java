package com.msa.fiveio.product.infrastructure.client;

import com.msa.fiveio.product.model.entity.Products;
import lombok.Getter;

@Getter
public class OrderProductInfoDto {

    private Products product;
    private Boolean isOrderable;

    public OrderProductInfoDto(Products product, Boolean isOrderable) {
        this.product = product;
        this.isOrderable = isOrderable;
    }
}
