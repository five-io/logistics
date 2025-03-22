package com.msa.fiveio.delivery.model.entity;

import com.msa.fiveio.common.auditing.BaseEntity;
import com.msa.fiveio.delivery.model.entity.enums.DeliveryStatus;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_deliveries")
@Entity
@Access(AccessType.FIELD)
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "delivery_id")
    private UUID id;

    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status", nullable = false)
    private DeliveryStatus deliveryStatus;

    @Column(name = "depart_hub_id", nullable = false)
    private UUID departHubId;

    @Column(name = "arrive_hub_id", nullable = false)
    private UUID arriveHubId;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    // 업체 배송 담당자 ID
    @Column(name = "company_delivery_manager_id", nullable = false)
    private Long companyDeliveryManagerId;

    @Embedded
    private Recipient recipient;

    @OneToMany(
        mappedBy = "delivery",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private List<DeliveryRoute> deliveryRoutes;

    @Builder(access = AccessLevel.PROTECTED)
    private Delivery(UUID orderId, DeliveryStatus deliveryStatus, UUID departHubId,
        UUID arriveHubId, String deliveryAddress, Long companyDeliveryManagerId,
        Recipient recipient,
        List<DeliveryRoute> deliveryRoutes) {
        this.orderId = orderId;
        this.deliveryStatus = deliveryStatus;
        this.departHubId = departHubId;
        this.arriveHubId = arriveHubId;
        this.deliveryAddress = deliveryAddress;
        this.companyDeliveryManagerId = companyDeliveryManagerId;
        this.recipient = recipient;
        this.deliveryRoutes = deliveryRoutes;
    }

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
            .build();
    }

    public void addDeliveryRoutes(List<DeliveryRoute> deliveryRoutes) {
        this.deliveryRoutes = deliveryRoutes;
    }

    private static Recipient createRecipient(String recipientName, String slackId) {
        return Recipient.builder()
            .recipientName(recipientName)
            .slackId(slackId)
            .build();
    }

    public void updateStatus(DeliveryStatus status) {
        this.deliveryStatus = status;
    }
}
