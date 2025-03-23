package com.msa.fiveio.product.model.repository;

import com.msa.fiveio.product.model.entity.Stocks;
import java.util.Optional;
import java.util.UUID;

public interface StocksRepository {

    Optional<Stocks> findById(UUID stockId);
}
