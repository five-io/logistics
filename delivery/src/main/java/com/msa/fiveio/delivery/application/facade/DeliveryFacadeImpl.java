package com.msa.fiveio.delivery.application.facade;

import com.msa.fiveio.delivery.application.usecase.DeliveryService;
import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryFacadeImpl implements DeliveryFacade {

    private final DeliveryService deliveryService;

    @Override
    public void createDelivery(DeliveryCreateRequestDto deliveryRequestDto) {
        deliveryService.createDelivery(deliveryRequestDto);
    }

    @Override
    public String updateStatus(UUID deliveryId, String status) {
        return deliveryService.updateStatus(deliveryId, status);
    }

}
