package com.msa.fiveio.product.application.facade;

import com.msa.fiveio.product.application.usecase.StockService;
import com.msa.fiveio.product.presentation.dto.response.StockUpdateResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StockFacadeImpl implements StockFacade {

    private final StockService stockService;

    @Override
    public StockUpdateResponseDto updateStock(UUID stockId, Long stockQuantity) {
        return stockService.updateStock(stockId, stockQuantity);
    }
}
