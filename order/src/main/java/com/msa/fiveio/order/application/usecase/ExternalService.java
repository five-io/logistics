package com.msa.fiveio.order.application.usecase;

import com.msa.fiveio.order.infrastructure.client.dto.response.ProductResponseDto;
import com.msa.fiveio.order.presentation.dto.request.OrderCreateRequestDto;
import java.util.UUID;

public interface ExternalService {

    void sendDeliveryRequest(UUID orderId, ProductResponseDto companyInfo,
        OrderCreateRequestDto orderInfo);

    ProductResponseDto sendProductRequest(OrderCreateRequestDto orderInfo);

    String getDeliveryStatus(UUID orderId);

    void rollbackStock(UUID orderId, Long quantity);
}
