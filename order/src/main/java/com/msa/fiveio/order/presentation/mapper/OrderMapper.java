package com.msa.fiveio.order.presentation.mapper;

import com.msa.fiveio.order.model.entity.Order;
import com.msa.fiveio.order.presentation.dto.response.OrderCreateResponseDto;
import com.msa.fiveio.order.presentation.dto.response.OrderResponseDto;
import java.util.UUID;

public class OrderMapper {

    public static OrderCreateResponseDto orderIdToOrderCreateResponseDto(UUID orderId) {
        return OrderCreateResponseDto.builder()
            .orderId(orderId)
            .build();
    }

    public static OrderResponseDto OrderToOrderResponseDto(Order order) {
        return OrderResponseDto.builder()
            .orderId(order.getOrderId())
            .requesterCompanyId(order.getRequesterCompanyId())
            .receiverCompanyId(order.getReceiverCompanyId())
            .productId(order.getProductId())
            .deliveryId(order.getDeliveryId())
            .quantity(order.getQuantity())
            .requestNotes(order.getRequestNotes())
            .build();
    }
}
