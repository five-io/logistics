package com.msa.fiveio.product.presentation.controller;

import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_COMPANY_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_HUB_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_MASTER;

import com.msa.fiveio.common.annotation.ApiPermission;
import com.msa.fiveio.product.application.facade.StockFacade;
import com.msa.fiveio.product.presentation.dto.response.StockUpdateResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
@Tag(name = "Stocks Service", description = "재고 서비스 API")
public class StocksController {

    private final StockFacade stockFacade;

    //재고조회

    //재고수정
    @ApiPermission(roles = {ROLE_MASTER, ROLE_HUB_MANAGER, ROLE_COMPANY_MANAGER})
    @Operation(summary = "재고 수량 수정", description = "재고 수량 수정 api 입니다.")
    @PutMapping("/{stockId}")
    public ResponseEntity<StockUpdateResponseDto> updateStock(
        @RequestParam("stockQuantity") long stockQuantity,
        @PathVariable("stockId") UUID stockId) {
        StockUpdateResponseDto responseDto = stockFacade.updateStock(stockId, stockQuantity);
        return ResponseEntity.ok(responseDto);
    }

    //재고삭제

    //재고 전체조회

}
