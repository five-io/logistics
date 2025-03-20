package com.msa.fiveio.product.application.facade;

import com.msa.fiveio.product.presentation.dto.ProductCreateRequestDto;
import com.msa.fiveio.product.presentation.dto.ProductCreateResponseDto;

public interface ProductFacade {

    ProductCreateResponseDto createProduct(ProductCreateRequestDto productRequestDto);
}
