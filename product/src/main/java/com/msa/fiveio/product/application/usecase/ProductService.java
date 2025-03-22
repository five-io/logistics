package com.msa.fiveio.product.application.usecase;

import com.msa.fiveio.product.infrastructure.client.OrderProductInfoDto;
import com.msa.fiveio.product.presentation.dto.ProductCreateRequestDto;
import com.msa.fiveio.product.presentation.dto.ProductCreateResponseDto;
import java.util.UUID;

public interface ProductService {

    //상품생성, 재고생성
    ProductCreateResponseDto createProduct(ProductCreateRequestDto RequestDto);

    //Order로 주문상품DTO 반환
    OrderProductInfoDto processOrderRequest(UUID productId, UUID receiverCompanyId,
            Long quantity);

    //상품조회(단건)

    //상품수정

    //상품삭제

    //상품search

}
