package com.msa.fiveio.order.model.entity;

import com.msa.fiveio.common.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "p_orders")
@Entity
public class Order extends BaseEntity {

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

    @Column(
        name = "total_amount",
        nullable = false,
        columnDefinition = "DOUBLE PRECISION DEFAULT 0.0 NOT NULL"
    )
    private Double totalAmount;

    @Builder
    private Order(UUID requesterCompanyId, UUID receiverCompanyId, UUID productId,
        Double totalAmount, Long quantity, String requestNotes) {
        this.requesterCompanyId = requesterCompanyId;
        this.receiverCompanyId = receiverCompanyId;
        this.productId = productId;
        this.quantity = quantity;
        this.requestNotes = requestNotes;
        this.totalAmount = totalAmount;
    }

    public static Order createOrder(
        UUID requesterCompanyId,
        UUID receiverCompanyId,
        UUID productId,
        Long quantity,
        String requestNotes
    ) {
        return Order.builder()
            .requesterCompanyId(requesterCompanyId)
            .receiverCompanyId(receiverCompanyId)
            .productId(productId)
            .quantity(quantity)
            .requestNotes(requestNotes)
            .build();
    }

    public void calculateTotalAmount(Double productPrice) {
        this.totalAmount = this.quantity * productPrice;
    }

    public void update(Long quantity, String requestNotes) {
        if (quantity != null) {
            validateUpdateQuantity(quantity);
            Double price = totalAmount / this.quantity;
            this.quantity = quantity;
            calculateTotalAmount(price);
        }
        if (requestNotes != null) {
            this.requestNotes = requestNotes;
        }
    }

    private void validateUpdateQuantity(Long quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("상품 개수는 1개부터 주문할 수 있습니다.");
        }
    }
}
