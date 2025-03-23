package com.msa.fiveio.product.presentation.controller;

import com.msa.fiveio.product.application.facade.StockFacade;
import com.msa.fiveio.product.presentation.dto.response.StockUpdateResponseDto;
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
public class StocksController {

    private final StockFacade stockFacade;

    //재고조회

    //재고수정
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
