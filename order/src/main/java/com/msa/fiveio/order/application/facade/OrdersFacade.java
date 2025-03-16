package com.msa.fiveio.order.application.facade;

import com.msa.fiveio.order.presentation.dto.request.OrderCreateRequestDto;
import com.msa.fiveio.order.presentation.dto.response.OrderCreateResponseDto;
import java.util.UUID;

public interface OrdersFacade {

    OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto);

    void updateDeliveryIdInOrder(UUID orderId, UUID deliveryId);

}
