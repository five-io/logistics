package com.msa.fiveio.order.application.usecase;

import com.msa.fiveio.order.infrastructure.client.dto.response.ProductResponseDto;
import com.msa.fiveio.order.model.entity.Order;
import com.msa.fiveio.order.presentation.dto.request.OrderSearchRequestDto;
import com.msa.fiveio.order.presentation.dto.request.OrderUpdateRequestDto;
import com.msa.fiveio.order.presentation.dto.response.OrderResponseDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Order createOrder(ProductResponseDto productInfo, Order order);

    Page<OrderResponseDto> readOrders(OrderSearchRequestDto requestDto, Pageable pageable);

    void updateOrder(Order order, OrderUpdateRequestDto requestDto);

    Order getOrder(UUID orderId);

    void cancelOrder(Order order, Long userId, String status);
}
