package com.msa.fiveio.delivery.presentation.mapper;

import com.msa.fiveio.delivery.model.entity.Delivery;
import com.msa.fiveio.delivery.presentation.dto.response.DeliveryResponseDto;

public class DeliveryMapper {

    public static DeliveryResponseDto DeliveryToDeliveryResponseDto(Delivery delivery) {
        return DeliveryResponseDto.builder()
            .deliveryId(delivery.getDeliveryId())
            .orderId(delivery.getOrderId())
            .deliveryStatus(delivery.getDeliveryStatus())
            .departHubId(delivery.getDepartHubId())
            .arriveHubId(delivery.getArriveHubId())
            .deliveryAddress(delivery.getDeliveryAddress())
            .companyDeliveryManagerId(delivery.getCompanyDeliveryManagerId())
            .recipient(delivery.getRecipient())
            .build();
    }
}

