package com.msa.fiveio.product.application.facade;

import com.msa.fiveio.product.presentation.dto.response.StockUpdateResponseDto;
import java.util.UUID;

public interface StockFacade {

    StockUpdateResponseDto updateStock(UUID stockId, Long stockQuantity);

}
