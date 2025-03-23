package com.msa.fiveio.product.application.usecase;

import com.msa.fiveio.product.presentation.dto.response.StockUpdateResponseDto;
import java.util.UUID;

public interface StockService {

    StockUpdateResponseDto updateStock(UUID stockId, Long stockQuantity);

}
