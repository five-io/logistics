package com.msa.fiveio.product.presentation.controller;

import com.msa.fiveio.product.application.facade.ProductFacade;
import com.msa.fiveio.product.presentation.dto.ProductCreateRequestDto;
import com.msa.fiveio.product.presentation.dto.ProductCreateResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Product Service", description = "상품 서비스 API")
public class ProductsController {

    private final ProductFacade productFacade;

    //상품등록
    @PostMapping
    public ResponseEntity<ProductCreateResponseDto> createProduct(
            @RequestBody ProductCreateRequestDto requestDto) {
        ProductCreateResponseDto responseDto = productFacade.createProduct(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    //상품조회

    //상품수정

    //상품삭제


}
