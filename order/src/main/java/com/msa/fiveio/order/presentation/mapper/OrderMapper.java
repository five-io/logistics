package com.msa.fiveio.order.presentation.mapper;

import com.msa.fiveio.order.model.entity.Order;
import com.msa.fiveio.order.presentation.dto.response.OrderCreateResponseDto;
import com.msa.fiveio.order.presentation.dto.response.OrderResponseDto;
import java.util.UUID;
import org.springframework.data.domain.Page;

public class OrderMapper {

    public static OrderCreateResponseDto orderIdToOrderCreateResponseDto(UUID orderId) {
        return OrderCreateResponseDto.builder()
            .orderId(orderId)
            .build();
    }

    public static Page<OrderResponseDto> toDtoPage(Page<Order> orders) {
        return orders.map(Order::toDto);
    }
}
