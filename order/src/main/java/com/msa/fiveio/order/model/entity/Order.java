package com.msa.fiveio.order.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "p_orders")
@Entity
public class Order {

    @Getter
    @Id
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

}
