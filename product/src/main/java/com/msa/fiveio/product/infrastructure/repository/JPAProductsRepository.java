package com.msa.fiveio.product.infrastructure.repository;

import com.msa.fiveio.product.model.entity.Products;
import com.msa.fiveio.product.model.repository.ProductsRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAProductsRepository extends ProductsRepository, JpaRepository<Products, UUID>,
        JPAProductsRepositoryCustom {

}
