package com.msa.fiveio.delivery.application.usecase;

import com.msa.fiveio.delivery.infrastructure.client.dto.DeliveryManagers;
import com.msa.fiveio.delivery.presentation.mapper.DeliveryMapper;
import com.msa.fiveio.delivery.infrastructure.messaging.DeliveryCreatedCompletedMessage;
import com.msa.fiveio.delivery.model.entity.Delivery;
import com.msa.fiveio.delivery.model.entity.DeliveryFactory;
import com.msa.fiveio.delivery.model.repository.DeliveryRepository;
import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final RabbitTemplate rabbitTemplate;

    @Value("${message.delivery.queue.created.completed}")
    private String queueCreatedCompleted;

    @Override
    public void createDelivery(
        DeliveryCreateRequestDto deliveryCreateRequestDto) {

//        DeliveryManagers managers = requestDeliveryManagerConfirmation(
//            deliveryCreateRequestDto.getDepartHubId(),
//            deliveryCreateRequestDto.getArriveHubId()
//        );

        // 목적지 허브 업체 배송 담당자 ID
//        Long companyDeliveryManager = managers.getCompanyDeliveryManager();
        Long companyDeliveryManager = 1L;
        // 허브 배송 담당자 ID
//        Long hubDeliveryManager = managers.getHubDeliveryManager();
        Long hubDeliveryManager = 2L;

        // 배송 생성
        Delivery delivery = createDelivery(deliveryCreateRequestDto, companyDeliveryManager);
        Delivery savedDelivery = deliveryRepository.save(delivery);
        log.info("배송 생성 완료, 주문 ID: {}, 배송 ID: {}", savedDelivery.getOrderId(),
            savedDelivery.getDeliveryId());

        sendDeliveryCreatedMessage(savedDelivery);
//        sendSlackMessageToDeliveryManager();
    }

    private Delivery createDelivery(DeliveryCreateRequestDto requestDto,
        Long companyDeliveryManager) {
        return DeliveryFactory.createDelivery(
            requestDto.getOrderId(),
            requestDto.getDepartHubId(),
            requestDto.getArriveHubId(),
            requestDto.getDeliveryAddress(),
            companyDeliveryManager,
            requestDto.getRecipientName(),
            requestDto.getRecipientSlackId()
        );
    }

    private DeliveryManagers requestDeliveryManagerConfirmation(UUID departHubId,
        UUID arriveHubId) {
        // 배송 --- 출발 허브 ID, 목적지 허브 ID ---> 사용자
        // 사용자 --- 출발 허브 배송 담당자 ID, 목적지 허브 업체 배송 담당자 ID ---> 배송
        return new DeliveryManagers();
    }

    private void sendDeliveryCreatedMessage(Delivery savedDelivery) {
        DeliveryCreatedCompletedMessage message = DeliveryCreatedCompletedMessage.builder()
            .orderId(savedDelivery.getOrderId())
            .deliveryId(savedDelivery.getDeliveryId())
            .build();
        rabbitTemplate.convertAndSend(queueCreatedCompleted, message);
    }

    private void sendSlackMessageToDeliveryManager() {
        // 발송 허브 담당자(hubDeliveryManager)에게 슬랙 메시지 보내는 요청 추가
        // 상품명, 수량, 요청 사항, 발송지, 경유지, 도착지, 배송 담당자 근무 시간 (09-18), 발송 허브 배송 담당자(수신인), 주문 ID ---> 슬랙
    }
}
