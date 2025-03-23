package com.msa.fiveio.delivery.application.usecase;

import com.msa.fiveio.common.exception.CustomException;
import com.msa.fiveio.common.exception.domain.DeliveryErrorCode;
import com.msa.fiveio.delivery.model.entity.Delivery;
import com.msa.fiveio.delivery.model.entity.enums.DeliveryStatus;
import com.msa.fiveio.delivery.model.repository.DeliveryRepository;
import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;
import com.msa.fiveio.delivery.presentation.dto.request.DeliverySearchRequestDto;
import com.msa.fiveio.delivery.presentation.dto.response.DeliveryResponseDto;
import com.msa.fiveio.delivery.presentation.mapper.DeliveryMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Override
    public DeliveryResponseDto createDelivery(
        DeliveryCreateRequestDto deliveryCreateRequestDto,
        Long companyDeliveryManagerId
    ) {
        Delivery delivery = Delivery.createDelivery(
            deliveryCreateRequestDto.getOrderId(),
            deliveryCreateRequestDto.getDepartHubId(),
            deliveryCreateRequestDto.getArriveHubId(),
            deliveryCreateRequestDto.getDeliveryAddress(),
            companyDeliveryManagerId,
            deliveryCreateRequestDto.getRecipientName(),
            deliveryCreateRequestDto.getRecipientSlackId()
        );

        Delivery savedDelivery = deliveryRepository.save(delivery);
        log.info("배송 생성 완료, 주문 ID: {}, 배송 ID: {}", savedDelivery.getOrderId(),
            savedDelivery.getId());
        return DeliveryMapper.DeliveryToDeliveryResponseDto(savedDelivery);
    }

    @Transactional
    @Override
    public String updateStatus(UUID deliveryId, String status) {
        Delivery delivery = findDeliveryById(deliveryId);

        DeliveryStatus deliveryStatus = DeliveryStatus.valueOf(status.toUpperCase());
        delivery.updateStatus(deliveryStatus);
        return deliveryStatus.name();
    }

    @Override
    public Page<DeliveryResponseDto> readDeliveries(DeliverySearchRequestDto requestDto,
        Pageable pageable) {
        Page<Delivery> deliveryPage = deliveryRepository.readDeliveries(requestDto, pageable);
        return deliveryPage.map(DeliveryMapper::DeliveryToDeliveryResponseDto);
    }

    @Override
    public DeliveryResponseDto readDelivery(UUID deliveryId) {
        Delivery delivery = findDeliveryById(deliveryId);
        return DeliveryMapper.DeliveryToDeliveryResponseDto(delivery);
    }

    @Override
    public String getDeliveryStatus(UUID orderId) {
        Delivery delivery = findDeliveryByOrderId(orderId);
        return delivery.getDeliveryStatus().name();
    }

    @Transactional
    @Override
    public void deleteDelivery(UUID deliveryId, Long userId) {
        Delivery delivery = findDeliveryById(deliveryId);
        if (!delivery.getDeliveryStatus().toString().equals("DELIVERED")) {
            throw new CustomException(DeliveryErrorCode.INVALID_DELIVERY_STATUS);
        }
        delivery.addDeletedField(userId);
    }

    @Transactional
    @Override
    public UUID cancelDelivery(UUID orderId, Long userId) {
        Delivery delivery = findDeliveryByOrderId(orderId);
        if (!delivery.getDeliveryStatus().toString().equals("HUB_PENDING")) {
            throw new CustomException(DeliveryErrorCode.CANCEL_INVALID_DELIVERY_STATUS);
        }
        delivery.addDeletedField(userId);
        return delivery.getId();
    }

    private Delivery findDeliveryById(UUID deliveryId) {
        return deliveryRepository.findById(deliveryId)
            .orElseThrow(() -> new CustomException(DeliveryErrorCode.DELIVERY_NOT_FOUND));
    }

    private Delivery findDeliveryByOrderId(UUID orderId) {
        return deliveryRepository.findByOrderId(orderId)
            .orElseThrow(() -> new CustomException(DeliveryErrorCode.DELIVERY_NOT_FOUND));
    }
}
