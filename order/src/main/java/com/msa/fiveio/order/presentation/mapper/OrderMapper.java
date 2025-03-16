package com.msa.fiveio.order.presentation.mapper;

import com.msa.fiveio.order.presentation.dto.response.OrderCreateResponseDto;
import java.util.UUID;

public class OrderMapper {

    public static OrderCreateResponseDto orderIdToOrderCreateResponseDto(UUID orderId) {
        return OrderCreateResponseDto.builder()
            .orderId(orderId)
            .build();
    }

}
