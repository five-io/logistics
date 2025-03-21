package com.msa.fiveio.order.infrastructure.client.dto.request;

import com.msa.fiveio.order.infrastructure.client.dto.response.CompanyResponseDto;
import com.msa.fiveio.order.presentation.dto.request.OrderCreateRequestDto;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryCreateRequestDto {

    private final UUID orderId;
    private final UUID departHubId;
    private final UUID arriveHubId;
    private final String deliveryAddress;
    private final String recipientName;
    private final String recipientSlackId;
    private final Long quantity;
    private final String requestNotes;
    private final String productName;


    public DeliveryCreateRequestDto(
        UUID orderId,
        CompanyResponseDto companyInfo,
        OrderCreateRequestDto orderInfo
    ) {
        this.orderId = orderId;
//        this.departHubId = companyInfo.getDepartHubId();
//        this.arriveHubId = companyInfo.getArriveHubId();
        this.departHubId = UUID.fromString("3f1a95f8-7c45-4a83-8e2d-b19d05a5f6a7");
        this.arriveHubId = UUID.fromString("a57c9d82-1e54-42d9-bd85-98a20dba1f4a");
        this.deliveryAddress = companyInfo.getDeliveryAddress();
        this.recipientName = orderInfo.getRecipientName();
        this.recipientSlackId = orderInfo.getRecipientSlackId();
        this.quantity = orderInfo.getQuantity();
        this.requestNotes = orderInfo.getRequestNotes();
        this.productName = companyInfo.getProductName();
    }
}
