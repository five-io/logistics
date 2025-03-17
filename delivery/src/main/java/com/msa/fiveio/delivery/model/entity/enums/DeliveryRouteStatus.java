package com.msa.fiveio.delivery.model.entity.enums;

public enum DeliveryRouteStatus {
    WAITING_AT_HUB,             // 허브 이동 대기중
    IN_TRANSIT_TO_HUB,          // 허브 이동중
    ARRIVED_AT_DESTINATION_HUB, // 목적지 허브 도착
    IN_DELIVERY                 // 배송중
}
