package com.msa.fiveio.product.application.facade;

import com.msa.fiveio.product.application.usecase.ProductService;
import com.msa.fiveio.product.presentation.dto.ProductCreateRequestDto;
import com.msa.fiveio.product.presentation.dto.ProductCreateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductFacadeImpl implements ProductFacade {

    private final ProductService productService;

    @Override
    public ProductCreateResponseDto createProduct(ProductCreateRequestDto productRequestDto) {
        return productService.createProduct(productRequestDto);
    }

}
