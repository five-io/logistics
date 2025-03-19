package com.msa.fiveio.order.infrastructure.repository;

import com.msa.fiveio.order.model.entity.Order;
import com.msa.fiveio.order.model.repository.OrderRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order, UUID>,
    JpaOrderRepositoryCustom {

    Optional<Order> readOrderByOrderId(UUID orderId);
}
