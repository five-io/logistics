package com.msa.fiveio.delivery.infrastructure.client.dto.request;

import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SlacksCreateRequestDto {

    private final UUID orderId;                     // o 주문 아이디
    private final String departHubName;             // x 도착 허브 이름
    private final String transitPoint;              // x 경유지
    private final String arriveHubName;             // x 출발 허브 이름
    private final String deliveryAddress;           // o 배송지
    private final String recipientName;             // o 수령인 이름
    private final String recipientSlackId;          // o 수령인 전화번호
    private final String companyDeliveryManager;    // x 업체 배송 담당자 이름
    private final String productName;               // o 상품명
    private final Long productQuantity;             // o 상품 개수
    private final String requestNotes;              // o 요청 사항

    @Builder
    public SlacksCreateRequestDto(DeliveryCreateRequestDto deliveryCreateRequestDto) {
        this.orderId = deliveryCreateRequestDto.getOrderId();
        this.departHubName = "도착 허브 이름(허브)";
        this.transitPoint = "경유지(배송 경로)";
        this.arriveHubName = "출발 허브 이름(허브)";
        this.deliveryAddress = deliveryCreateRequestDto.getDeliveryAddress();
        this.recipientName = deliveryCreateRequestDto.getRecipientName();
        this.recipientSlackId = deliveryCreateRequestDto.getRecipientSlackId();
        this.companyDeliveryManager = "업체 배송 담당자 이름(사용자)";
        this.productName = deliveryCreateRequestDto.getProductName();
        this.productQuantity = deliveryCreateRequestDto.getQuantity();
        this.requestNotes = deliveryCreateRequestDto.getRequestNotes();
    }
}
