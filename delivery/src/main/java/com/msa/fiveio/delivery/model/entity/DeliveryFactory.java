package com.msa.fiveio.delivery.model.entity;

import com.msa.fiveio.delivery.model.entity.enums.DeliveryStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeliveryFactory {

    public static Delivery createDelivery(
        UUID orderId,
        UUID departHubId,
        UUID arriveHubId,
        String deliveryAddress,
        Long companyDeliveryManagerId,
        String recipientName,
        String recipientSlackId
    ) {
        return Delivery.builder()
            .orderId(orderId)
            .deliveryStatus(DeliveryStatus.HUB_PENDING)
            .departHubId(departHubId)
            .arriveHubId(arriveHubId)
            .deliveryAddress(deliveryAddress)
            .companyDeliveryManagerId(companyDeliveryManagerId)
            .recipient(createRecipient(recipientName, recipientSlackId))
            .deliveryRoutes(createDeliveryRoutes())
            .build();
    }

    // 배송 경로 생성 로직 추가
    public static List<DeliveryRoute> createDeliveryRoutes(
    ) {
        List<DeliveryRoute> deliveryRoutes = new ArrayList<>();
        return deliveryRoutes;
    }

    private static Recipient createRecipient(String recipientName, String slackId) {
        return Recipient.builder()
            .recipientName(recipientName)
            .slackId(slackId)
            .build();
    }
}
