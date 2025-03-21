package com.msa.fiveio.product.application.facade;

import com.msa.fiveio.product.presentation.dto.OrderProductGetResponseDto;
import com.msa.fiveio.product.presentation.dto.ProductCreateRequestDto;
import com.msa.fiveio.product.presentation.dto.ProductCreateResponseDto;
import java.util.UUID;

public interface ProductFacade {

    ProductCreateResponseDto createProduct(ProductCreateRequestDto productRequestDto);

    OrderProductGetResponseDto processOrderRequest(UUID productId, UUID receiverCompanyId,
            Long quantity);


}
