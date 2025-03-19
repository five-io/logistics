package com.msa.fiveio.order.model.entity;

import java.util.UUID;

public class OrderFactory {

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

}
