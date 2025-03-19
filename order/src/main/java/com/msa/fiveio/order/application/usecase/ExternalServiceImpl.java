package com.msa.fiveio.order.application.usecase;

import com.msa.fiveio.order.infrastructure.client.DeliveryClient;
import com.msa.fiveio.order.infrastructure.client.dto.request.DeliveryCreateRequestDto;
import com.msa.fiveio.order.infrastructure.client.dto.response.CompanyResponseDto;
import com.msa.fiveio.order.presentation.dto.request.OrderCreateRequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalServiceImpl implements ExternalService {

    private final DeliveryClient deliveryClient;

    @Override
    public void sendDeliveryRequest(UUID orderId, CompanyResponseDto companyInfo,
        OrderCreateRequestDto orderInfo) {
        try {
            DeliveryCreateRequestDto request = new DeliveryCreateRequestDto(orderId, companyInfo,
                orderInfo);
            deliveryClient.createDelivery(request);
        } catch (Exception e) {
            log.error("Failed to create delivery for orderId: {}", orderId, e);
            throw new IllegalArgumentException("Failed to request delivery", e);
        }
    }

    @Override
    public CompanyResponseDto sendCompanyRequest(OrderCreateRequestDto orderInfo) {
        return CompanyResponseDto.builder()
            .deliveryAddress("Test Delivery Address")
            .requesterCompanyId(UUID.randomUUID())
            .departHubId(UUID.randomUUID())
            .arriveHubId(UUID.randomUUID())
            .productPrice(1000.0)
            .build();
//        return companyClient.getCompanyInfo(orderInfo.getProductId(),
//            orderInfo.getReceiverCompanyId(), orderInfo.getQuantity());
    }
}
