package com.msa.fiveio.order.model.repository;

import com.msa.fiveio.order.model.entity.Order;
import com.msa.fiveio.order.presentation.dto.request.OrderSearchRequestDto;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderRepository {

    Order save(Order hubs);

    Optional<Order> findById(UUID id);

    Optional<Order> readOrderByOrderId(UUID orderId);

    Page<Order> readOrders(OrderSearchRequestDto requestDto, Pageable pageable);
}
