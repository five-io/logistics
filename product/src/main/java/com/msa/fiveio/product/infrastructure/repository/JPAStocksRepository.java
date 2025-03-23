package com.msa.fiveio.product.infrastructure.repository;

import com.msa.fiveio.product.model.entity.Stocks;
import com.msa.fiveio.product.model.repository.StocksRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAStocksRepository extends StocksRepository, JpaRepository<Stocks, UUID> {

}
