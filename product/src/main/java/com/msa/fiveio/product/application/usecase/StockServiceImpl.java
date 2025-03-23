package com.msa.fiveio.product.application.usecase;

import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.ProductErrorCode;
import com.msa.fiveio.product.model.entity.Stocks;
import com.msa.fiveio.product.model.repository.StocksRepository;
import com.msa.fiveio.product.presentation.dto.response.StockUpdateResponseDto;
import com.msa.fiveio.product.presentation.mapper.StocksMapper;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StocksRepository stocksRepository;

    @Override
    public StockUpdateResponseDto updateStock(UUID stockId, Long stockQuantity) {
        Stocks stock = stocksRepository.findById(stockId).orElseThrow(
                () -> new CustomException(ProductErrorCode.STOCK_NOT_FOUND));
        stock.update(stockQuantity);
        StockUpdateResponseDto RequestDto = StocksMapper.EntityToStockUpdateResponseDto(stock);
        return RequestDto;
    }
}
