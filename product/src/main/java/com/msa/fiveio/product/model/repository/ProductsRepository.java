package com.msa.fiveio.product.model.repository;

import com.msa.fiveio.product.model.entity.Products;
import java.util.Optional;
import java.util.UUID;

public interface ProductsRepository {

    Products save(Products product);

    Optional<Products> findById(UUID productId);
}
