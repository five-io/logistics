package com.msa.fiveio.delivery.infrastructure.client.dto.request;

import com.msa.fiveio.delivery.infrastructure.client.dto.response.HubsResponseDto;
import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;


@Getter

public class SlacksCreateRequestDto {

    private final UUID orderId;                     // 주문 아이디
    private final String departHubName;             // 도착 허브 이름
    private final String transitPoint;              // 경유지
    private final String arriveHubName;             // 출발 허브 이름
    private final String deliveryAddress;           // 배송지
    private final String recipientName;             // 수령인 이름
    private final String recipientSlackId;          // 수령인 전화번호
    private final String companyDeliveryManager;    // 업체 배송 담당자 이름
    private final String productName;               // 상품명
    private final Long productQuantity;             // 상품 개수
    private final String requestNotes;              // 요청 사항

    @Builder
    public SlacksCreateRequestDto(
        DeliveryCreateRequestDto deliveryCreateRequestDto,
        HubsResponseDto departHub,
        HubsResponseDto arriveHub,
        String wayPoints,
        String deliveryManagerName
    ) {
        this.orderId = deliveryCreateRequestDto.getOrderId();
        this.departHubName = departHub.getHubName();
        this.transitPoint = wayPoints;
        this.arriveHubName = arriveHub.getHubName();
        this.deliveryAddress = deliveryCreateRequestDto.getDeliveryAddress();
        this.recipientName = deliveryCreateRequestDto.getRecipientName();
        this.recipientSlackId = deliveryCreateRequestDto.getRecipientSlackId();
        this.companyDeliveryManager = deliveryManagerName;
        this.productName = deliveryCreateRequestDto.getProductName();
        this.productQuantity = deliveryCreateRequestDto.getQuantity();
        this.requestNotes = deliveryCreateRequestDto.getRequestNotes();
    }
}
