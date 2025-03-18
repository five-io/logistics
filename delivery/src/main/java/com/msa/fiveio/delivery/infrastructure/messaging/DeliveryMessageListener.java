package com.msa.fiveio.delivery.infrastructure.messaging;

import com.msa.fiveio.delivery.application.facade.DeliveryFacade;
import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliveryMessageListener {

    private final DeliveryFacade deliveryFacade;

    @RabbitListener(queues = "${message.orderToDelivery.queue.delivery}")
    public void receiveOrderMessage(DeliveryCreateRequestDto requestDto) {
        log.info("Received DeliveryCreateRequestDto: {}", requestDto.getOrderId());
        deliveryFacade.createDelivery(requestDto);
    }
}
