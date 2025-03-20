package com.msa.fiveio.product.application.usecase;

import com.msa.fiveio.product.presentation.dto.ProductCreateRequestDto;
import com.msa.fiveio.product.presentation.dto.ProductCreateResponseDto;

public interface ProductService {

    //상품생성, 재고생성
    ProductCreateResponseDto createProduct(ProductCreateRequestDto RequestDto);

    //상품조회(단건)

    //상품수정

    //상품삭제

    //상품search

}
