package com.msa.fiveio.delivery.application.facade;

import com.msa.fiveio.delivery.application.usecase.DeliveryService;
import com.msa.fiveio.delivery.application.usecase.ExternalService;
import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;
import com.msa.fiveio.delivery.presentation.dto.request.DeliverySearchRequestDto;
import com.msa.fiveio.delivery.presentation.dto.response.DeliveryResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryFacadeImpl implements DeliveryFacade {

    private final DeliveryService deliveryService;
    private final ExternalService externalService;

    @Override
    public void createDelivery(DeliveryCreateRequestDto deliveryRequestDto) {
        deliveryService.createDelivery(deliveryRequestDto);
        externalService.sendSlackRequest(deliveryRequestDto);
    }

    @Override
    public String updateStatus(UUID deliveryId, String status) {
        return deliveryService.updateStatus(deliveryId, status);
    }

    @Override
    public Page<DeliveryResponseDto> readDeliveries(DeliverySearchRequestDto requestDto,
        Pageable pageable) {
        return deliveryService.readDeliveries(requestDto, pageable);
    }

    @Override
    public DeliveryResponseDto readDelivery(UUID deliveryId) {
        return deliveryService.readDelivery(deliveryId);
    }

}
