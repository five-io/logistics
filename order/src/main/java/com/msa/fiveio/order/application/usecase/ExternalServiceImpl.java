package com.msa.fiveio.order.application.usecase;

import com.msa.fiveio.order.infrastructure.client.DeliveryClient;
import com.msa.fiveio.order.infrastructure.client.ProductClient;
import com.msa.fiveio.order.infrastructure.client.dto.request.DeliveryCreateRequestDto;
import com.msa.fiveio.order.infrastructure.client.dto.response.ProductResponseDto;
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
    private final ProductClient productClient;

    @Override
    public void sendDeliveryRequest(UUID orderId, ProductResponseDto productInfo,
        OrderCreateRequestDto orderInfo) {
        try {
            DeliveryCreateRequestDto request = new DeliveryCreateRequestDto(orderId, productInfo,
                orderInfo);
            deliveryClient.createDelivery(request);
        } catch (Exception e) {
            log.error("Failed to create delivery for orderId: {}", orderId, e);
            throw new IllegalArgumentException("Failed to request delivery", e);
        }
    }

    @Override
    public ProductResponseDto sendProductRequest(OrderCreateRequestDto orderInfo) {
        try {
            return productClient.processOrderRequest(orderInfo.getProductId(),
                orderInfo.getReceiverCompanyId(), orderInfo.getQuantity());
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to process order request", e);
        }
    }

    @Override
    public String getDeliveryStatus(UUID orderId) {
        try {
            return deliveryClient.getDeliveryStatus(orderId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to get delivery status", e);
        }
    }

    @Override
    public void rollbackStock(UUID orderId, Long quantity) {
        try {
            productClient.rollbackStock(orderId, quantity);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to rollback stock", e);
        }
    }
}
