package com.msa.fiveio.order.application.facade;

import com.msa.fiveio.order.presentation.dto.request.OrderCreateRequestDto;
import com.msa.fiveio.order.presentation.dto.request.OrderSearchRequestDto;
import com.msa.fiveio.order.presentation.dto.response.OrderCreateResponseDto;
import com.msa.fiveio.order.presentation.dto.response.OrderResponseDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrdersFacade {

    OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto);

    void updateDeliveryIdInOrder(UUID orderId, UUID deliveryId);

    Page<OrderResponseDto> readOrders(OrderSearchRequestDto requestDto, Pageable pageable);
}
