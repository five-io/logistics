package com.msa.fiveio.order.infrastructure.client.dto;

import com.msa.fiveio.order.presentation.dto.request.OrderCreateRequestDto;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryCreateRequest {

    private final UUID orderId;
    private final UUID departHubId;
    private final UUID arriveHubId;
    private final String deliveryAddress;
    private final String recipientName;
    private final String recipientSlackId;

    public DeliveryCreateRequest(
        UUID orderId,
        CompanyResponseDto companyInfo,
        OrderCreateRequestDto orderInfo
    ) {
        this.orderId = orderId;
        this.departHubId = companyInfo.getDepartHubId();
        this.arriveHubId = companyInfo.getArriveHubId();
        this.deliveryAddress = companyInfo.getDeliveryAddress();
        this.recipientName = orderInfo.getRecipientName();
        this.recipientSlackId = orderInfo.getRecipientSlackId();
    }
}
