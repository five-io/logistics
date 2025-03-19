package com.msa.fiveio.order.application.usecase;

import com.msa.fiveio.order.infrastructure.client.dto.response.CompanyResponseDto;
import com.msa.fiveio.order.presentation.dto.request.OrderCreateRequestDto;
import java.util.UUID;

public interface ExternalService {

    void sendDeliveryRequest(UUID orderId, CompanyResponseDto companyInfo,
        OrderCreateRequestDto orderInfo);

    CompanyResponseDto sendCompanyRequest(OrderCreateRequestDto orderInfo);
}
