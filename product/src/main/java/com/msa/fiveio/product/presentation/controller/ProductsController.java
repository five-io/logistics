package com.msa.fiveio.product.presentation.controller;

import com.msa.fiveio.product.application.facade.ProductFacade;
import com.msa.fiveio.product.presentation.dto.request.ProductCreateRequestDto;
import com.msa.fiveio.product.presentation.dto.response.OrderProductGetResponseDto;
import com.msa.fiveio.product.presentation.dto.response.ProductCreateResponseDto;
import com.msa.fiveio.product.presentation.dto.response.ProductGetResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    //상품등록, 재고생성
    @PostMapping
    public ResponseEntity<ProductCreateResponseDto> createProduct(
            @RequestBody ProductCreateRequestDto requestDto) {
        ProductCreateResponseDto responseDto = productFacade.createProduct(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    //상품삭제
    //상품수정

    //상품조회(단건)
    @GetMapping("/{productId}")
    public ResponseEntity<ProductGetResponseDto> getProduct(@PathVariable UUID productId) {
        ProductGetResponseDto responseDto = productFacade.getProduct(productId);
        return ResponseEntity.ok(responseDto);
    }


    //order 로부터 정보 받아와서 다시 반환
    @GetMapping("/order")
    OrderProductGetResponseDto processOrderRequest(@RequestParam UUID productId,
            @RequestParam UUID receiverCompanyId, @RequestParam Long quantity) {
        OrderProductGetResponseDto responseDto = productFacade.processOrderRequest(productId,
                receiverCompanyId, quantity);
        return responseDto;
    }

    //order에서 주문 취소할 경우 -> 재고수량 되돌리기
    @PatchMapping("/{id}/rollback")
    ResponseEntity<String> rollbackStock(@PathVariable("id") UUID productId,
            @RequestParam Long quantity) {
        productFacade.rollbackStock(productId, quantity);
        return ResponseEntity.ok("주문취소에 의해 재고가 롤백되었습니다");
    }

}
