package com.msa.fiveio.order.infrastructure.messaging;

import com.msa.fiveio.order.application.facade.OrdersFacade;
import com.msa.fiveio.order.infrastructure.messaging.dto.DeliveryCreatedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderMessageListener {

    private final OrdersFacade ordersFacade;

    @RabbitListener(queues = "${message.delivery.queue.created.completed}")
    public void receiveDeliveryMessage(DeliveryCreatedMessage message) {
        ordersFacade.updateDeliveryIdInOrder(message.getOrderId(), message.getDeliveryId());
    }

}
