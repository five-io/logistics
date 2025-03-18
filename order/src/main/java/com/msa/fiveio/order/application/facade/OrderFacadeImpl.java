package com.msa.fiveio.order.application.facade;

import com.msa.fiveio.order.application.usecase.OrderService;
import com.msa.fiveio.order.presentation.dto.request.OrderCreateRequestDto;
import com.msa.fiveio.order.presentation.dto.request.OrderSearchRequestDto;
import com.msa.fiveio.order.presentation.dto.response.OrderCreateResponseDto;
import com.msa.fiveio.order.presentation.dto.response.OrderResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrdersFacade {

    private final OrderService orderService;

    @Override
    public OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto) {
        return orderService.createOrder(orderCreateRequestDto);
    }

    @Override
    public void updateDeliveryIdInOrder(UUID orderId, UUID deliveryId) {
        orderService.updateDeliveryIdInOrder(orderId, deliveryId);
    }

    @Override
    public Page<OrderResponseDto> readOrders(OrderSearchRequestDto requestDto, Pageable pageable) {
        return orderService.readOrders(requestDto, pageable);
    }

}