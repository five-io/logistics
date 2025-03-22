package com.msa.fiveio.order.application.usecase;

import com.msa.fiveio.order.infrastructure.client.dto.response.ProductResponseDto;
import com.msa.fiveio.order.model.repository.OrderRepository;
import com.msa.fiveio.order.presentation.dto.request.OrderSearchRequestDto;
import com.msa.fiveio.order.presentation.dto.request.OrderUpdateRequestDto;
import com.msa.fiveio.order.presentation.dto.response.OrderResponseDto;
import com.msa.fiveio.order.presentation.mapper.OrderMapper;
import com.msa.fiveio.order.model.entity.Order;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(
    readOnly = true
)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public Order createOrder(ProductResponseDto productInfo, Order order) {
        order.calculateTotalAmount(productInfo.getProductPrice());

        Order savedOrder = orderRepository.save(order);
        log.info("Order created: {}", savedOrder.getOrderId());
        return savedOrder;
    }

    @Override
    public Page<OrderResponseDto> readOrders(OrderSearchRequestDto requestDto, Pageable pageable) {
        Page<Order> orderPage = orderRepository.readOrders(requestDto, pageable);
        return orderPage.map(OrderMapper::OrderToOrderResponseDto);
    }

    @Override
    public void updateOrder(Order order, OrderUpdateRequestDto requestDto) {
        order.update(
            requestDto.getQuantity(),
            requestDto.getRequestNotes()
        );
    }

    @Override
    public Order getOrder(UUID orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public void cancelOrder(Order order, Long userId, String status) {
        if (!status.equals("HUB_PENDING")) {
            throw new RuntimeException("Order status is not HUB_PENDING");
        }
        order.addDeletedField(userId);
        log.info("Order cancelled: {}", order.getOrderId());
    }
}
