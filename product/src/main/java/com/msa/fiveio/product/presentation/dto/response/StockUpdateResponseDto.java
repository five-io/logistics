package com.msa.fiveio.product.presentation.dto.response;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StockUpdateResponseDto {

    private UUID stockId;
    private Long stockQuantity;

    @Builder
    public StockUpdateResponseDto(UUID stockId, Long stockQuantity) {
        this.stockId = stockId;
        this.stockQuantity = stockQuantity;
    }
}
