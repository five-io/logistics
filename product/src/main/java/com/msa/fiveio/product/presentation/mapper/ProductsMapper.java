package com.msa.fiveio.product.presentation.mapper;

import com.msa.fiveio.product.model.entity.Products;
import com.msa.fiveio.product.model.entity.Stocks;
import com.msa.fiveio.product.presentation.dto.ProductCreateRequestDto;
import com.msa.fiveio.product.presentation.dto.ProductCreateResponseDto;

public class ProductsMapper {

    public static Products ProductCreateRequestDtoToEntity(ProductCreateRequestDto requestDto) {
        return Products.builder()
                .productName(requestDto.getProductName())
                .productDetail(requestDto.getProductDetail())
                .hubId(requestDto.getHubId())
                .companyId(requestDto.getCompanyId())
                .stocks(Stocks.builder().quantity(0).build()) //재고 초기세팅
                .build();
    }

    public static ProductCreateResponseDto EntityToProductCreateResponseDto(Products products) {
        return ProductCreateResponseDto.builder()
                .productId(products.getId())
                .productName(products.getProductName())
                .productDetail(products.getProductDetail())
                .hubId(products.getHubId())
                .companyId(products.getCompanyId())
                .build();
    }
}
