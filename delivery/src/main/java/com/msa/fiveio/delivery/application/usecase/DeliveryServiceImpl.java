package com.msa.fiveio.delivery.application.usecase;

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
    public void createDelivery(
        DeliveryCreateRequestDto deliveryCreateRequestDto) {
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
            savedDelivery.getId());
    }

    @Transactional
    @Override
    public String updateStatus(UUID deliveryId, String status) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
            .orElseThrow(() -> new IllegalArgumentException("Delivery not found"));

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
        Delivery delivery = deliveryRepository.findById(deliveryId)
            .orElseThrow(() -> new IllegalArgumentException("Delivery not found"));
        return DeliveryMapper.DeliveryToDeliveryResponseDto(delivery);
    }

    private Delivery createDelivery(DeliveryCreateRequestDto requestDto,
        Long companyDeliveryManager) {
        return Delivery.createDelivery(
            requestDto.getOrderId(),
            requestDto.getDepartHubId(),
            requestDto.getArriveHubId(),
            requestDto.getDeliveryAddress(),
            companyDeliveryManager,
            requestDto.getRecipientName(),
            requestDto.getRecipientSlackId()
        );
    }
}
