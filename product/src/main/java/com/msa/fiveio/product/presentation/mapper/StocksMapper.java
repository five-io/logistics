package com.msa.fiveio.product.presentation.mapper;

import com.msa.fiveio.product.model.entity.Stocks;
import com.msa.fiveio.product.presentation.dto.response.StockUpdateResponseDto;

public class StocksMapper {

    public static StockUpdateResponseDto EntityToStockUpdateResponseDto(Stocks stocks) {
        return StockUpdateResponseDto.builder()
                .stockId(stocks.getId())
                .stockQuantity(stocks.getQuantity())
                .build();
    }

}
