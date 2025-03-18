package com.msa.fiveio.order.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "p_orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Column(name = "requester_company_id", nullable = false)
    private UUID requesterCompanyId;

    @Column(name = "receiver_company_id", nullable = false)
    private UUID receiverCompanyId;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "delivery_id")
    private UUID deliveryId;

    @Column(nullable = false)
    private Long quantity;

    @Column(name = "request_notes", columnDefinition = "TEXT")
    private String requestNotes;

    public void updateDeliveryId(UUID deliveryId) {
        this.deliveryId = deliveryId;
    }

    @Builder(access = AccessLevel.PROTECTED)
    public Order(UUID requesterCompanyId, UUID receiverCompanyId, UUID productId,
        UUID deliveryId, Long quantity, String requestNotes) {
        this.requesterCompanyId = requesterCompanyId;
        this.receiverCompanyId = receiverCompanyId;
        this.productId = productId;
        this.deliveryId = deliveryId;
        this.quantity = quantity;
        this.requestNotes = requestNotes;
    }

}
