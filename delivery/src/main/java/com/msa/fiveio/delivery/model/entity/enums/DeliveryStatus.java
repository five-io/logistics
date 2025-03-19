package com.msa.fiveio.delivery.model.entity.enums;

public enum DeliveryStatus {
    HUB_PENDING,                // 허브 대기중
    IN_TRANSIT_TO_HUB,          // 허브 이동중
    ARRIVED_AT_DESTINATION_HUB, // 목적지 허브 도착
    COMPANY_TRANSIT,            // 업체 이동중
    OUT_FOR_DELIVERY,           // 배송중
    DELIVERED                   // 배송완료
}
