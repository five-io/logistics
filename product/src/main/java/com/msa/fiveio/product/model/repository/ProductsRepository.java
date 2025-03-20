package com.msa.fiveio.product.model.repository;

import com.msa.fiveio.product.model.entity.Products;

public interface ProductsRepository {

    Products save(Products product);
}
