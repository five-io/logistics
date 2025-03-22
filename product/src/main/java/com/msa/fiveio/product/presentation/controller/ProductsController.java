package com.msa.fiveio.product.presentation.controller;

import com.msa.fiveio.product.application.facade.ProductFacade;
import com.msa.fiveio.product.presentation.dto.OrderProductGetResponseDto;
import com.msa.fiveio.product.presentation.dto.ProductCreateRequestDto;
import com.msa.fiveio.product.presentation.dto.ProductCreateResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    //상품삭제
    //상품수정
    //상품조회


    //order 로부터 정보 받아와서 다시 반환
    @GetMapping("/order")
    OrderProductGetResponseDto processOrderRequest(@RequestParam UUID productId,
            @RequestParam UUID receiverCompanyId, @RequestParam Long quantity) {
        OrderProductGetResponseDto responseDto = productFacade.processOrderRequest(productId,
                receiverCompanyId, quantity);
        return responseDto;
    }

    //order에서 주문 취소할 경우 -> 재고수량 되돌리기

}
