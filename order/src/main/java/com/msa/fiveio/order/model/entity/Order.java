package com.msa.fiveio.order.model.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private Long quantity;

    @Column(name = "request_notes", columnDefinition = "TEXT")
    private String requestNotes;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Builder
    private Order(UUID requesterCompanyId, UUID receiverCompanyId, UUID productId,
        Double totalPrice, Long quantity, String requestNotes) {
        this.requesterCompanyId = requesterCompanyId;
        this.receiverCompanyId = receiverCompanyId;
        this.productId = productId;
        this.quantity = quantity;
        this.requestNotes = requestNotes;
        this.totalPrice = totalPrice;
    }

    public static Order createOrder(
        UUID requesterCompanyId,
        UUID receiverCompanyId,
        UUID productId,
        Long quantity,
        String requestNotes,
        Double totalPrice
    ) {
        return Order.builder()
            .requesterCompanyId(requesterCompanyId)
            .receiverCompanyId(receiverCompanyId)
            .productId(productId)
            .quantity(quantity)
            .requestNotes(requestNotes)
            .totalPrice(totalPrice)
            .build();
    }
}
