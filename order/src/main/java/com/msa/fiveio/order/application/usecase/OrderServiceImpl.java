package com.msa.fiveio.order.application.usecase;

import com.msa.fiveio.order.infrastructure.client.CompanyClient;
import com.msa.fiveio.order.infrastructure.client.dto.CompanyResponseDto;
import com.msa.fiveio.order.infrastructure.messaging.dto.DeliveryCreateRequest;
import com.msa.fiveio.order.presentation.mapper.OrderMapper;
import com.msa.fiveio.order.infrastructure.repository.JpaOrderRepository;
import com.msa.fiveio.order.model.entity.Order;
import com.msa.fiveio.order.model.entity.OrderFactory;
import com.msa.fiveio.order.presentation.dto.request.OrderCreateRequestDto;
import com.msa.fiveio.order.presentation.dto.response.OrderCreateResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final RabbitTemplate rabbitTemplate;
    private final JpaOrderRepository jpaOrderRepository;
    private final CompanyClient companyClient;

    @Value("${message.orderToDelivery.queue.delivery}")
    private String queueDelivery;

    @Override
    public OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto) {
        // 업체에게 주문 가능 여부 확인
        CompanyResponseDto companyInfo = sendCompanyRequest(orderCreateRequestDto);

        UUID orderId = UUID.randomUUID();
        Order order = createOrder(orderId, companyInfo.getRequesterCompanyId(),
            orderCreateRequestDto);

        sendDeliveryRequest(orderId, companyInfo, orderCreateRequestDto);

        Order savedOrder = jpaOrderRepository.save(order);
        return OrderMapper.orderIdToOrderCreateResponseDto(savedOrder.getOrderId());
    }

    @Override
    public void updateDeliveryIdInOrder(UUID orderId, UUID deliveryId) {
        Order order = jpaOrderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));

        order.updateDeliveryId(deliveryId);
        jpaOrderRepository.save(order);
    }

    private Order createOrder(UUID orderId, UUID requesterCompanyId,
        OrderCreateRequestDto orderInfo) {
        return OrderFactory.createOrder(
            orderId,
            requesterCompanyId,
            orderInfo.getReceiverCompanyId(),
            orderInfo.getProductId(),
            orderInfo.getQuantity(),
            orderInfo.getRequestNotes()
        );
    }

    private void sendDeliveryRequest(UUID orderId, CompanyResponseDto companyInfo,
        OrderCreateRequestDto orderInfo) {
        DeliveryCreateRequest deliveryCreateRequest = DeliveryCreateRequest.builder()
            .orderId(orderId)
            .departHubId(companyInfo.getDepartHubId())
            .arriveHubId(companyInfo.getArriveHubId())
            .deliveryAddress(companyInfo.getDeliveryAddress())
            .recipientName(orderInfo.getRecipientName())
            .recipientSlackId(orderInfo.getRecipientSlackId())
            .build();
        rabbitTemplate.convertAndSend(queueDelivery, deliveryCreateRequest);
    }

    private CompanyResponseDto sendCompanyRequest(OrderCreateRequestDto orderInfo) {
        return companyClient.getCompanyInfo(orderInfo.getProductId(),
            orderInfo.getReceiverCompanyId(), orderInfo.getQuantity());
    }

}
