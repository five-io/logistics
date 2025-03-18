package com.msa.fiveio.order.application.usecase;

import com.msa.fiveio.order.infrastructure.client.CompanyClient;
import com.msa.fiveio.order.infrastructure.client.dto.CompanyResponseDto;
import com.msa.fiveio.order.infrastructure.messaging.dto.DeliveryCreateRequest;
import com.msa.fiveio.order.model.repository.OrderRepository;
import com.msa.fiveio.order.presentation.dto.request.OrderSearchRequestDto;
import com.msa.fiveio.order.presentation.dto.response.OrderResponseDto;
import com.msa.fiveio.order.presentation.mapper.OrderMapper;
import com.msa.fiveio.order.model.entity.Order;
import com.msa.fiveio.order.presentation.dto.request.OrderCreateRequestDto;
import com.msa.fiveio.order.presentation.dto.response.OrderCreateResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(
    readOnly = true
)
public class OrderServiceImpl implements OrderService {

    private final RabbitTemplate rabbitTemplate;
    private final OrderRepository orderRepository;
    private final CompanyClient companyClient;

    @Value("${message.orderToDelivery.queue.delivery}")
    private String queueDelivery;

    @Transactional
    @Override
    public OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto) {
        // 업체에게 주문 가능 여부 확인
        CompanyResponseDto companyInfo = sendCompanyRequest(orderCreateRequestDto);

        Order order = orderCreateRequestDto.createOrder();

        Order savedOrder = orderRepository.save(order);
        sendDeliveryRequest(savedOrder.getOrderId(), companyInfo, orderCreateRequestDto);

        log.info("Order created: {}", savedOrder.getOrderId());
        return OrderMapper.orderIdToOrderCreateResponseDto(savedOrder.getOrderId());
    }

    @Transactional
    @Override
    public void updateDeliveryIdInOrder(UUID orderId, UUID deliveryId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        order.updateDeliveryId(deliveryId);
    }

    @Override
    public Page<OrderResponseDto> readOrders(OrderSearchRequestDto requestDto, Pageable pageable) {
        Page<Order> orderPage = orderRepository.readOrders(requestDto, pageable);
        return orderPage.map(OrderMapper::OrderToOrderResponseDto);
    }

    @Override
    public OrderResponseDto readOrder(UUID orderId) {
        Order order = orderRepository.readOrderByOrderId(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderMapper.OrderToOrderResponseDto(order);
    }

    private void sendDeliveryRequest(UUID orderId, CompanyResponseDto companyInfo,
        OrderCreateRequestDto orderInfo) {
        DeliveryCreateRequest request = new DeliveryCreateRequest(orderId, companyInfo, orderInfo);

        rabbitTemplate.convertAndSend(queueDelivery, request);
    }

    private CompanyResponseDto sendCompanyRequest(OrderCreateRequestDto orderInfo) {
        return CompanyResponseDto.builder()
            .deliveryAddress("Test Delivery Address")
            .requesterCompanyId(UUID.randomUUID())
            .departHubId(UUID.randomUUID())
            .arriveHubId(UUID.randomUUID())
            .build();
//        return companyClient.getCompanyInfo(orderInfo.getProductId(),
//            orderInfo.getReceiverCompanyId(), orderInfo.getQuantity());
    }

}
