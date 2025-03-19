package com.msa.fiveio.delivery.application.facade;

import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;
import java.util.UUID;

public interface DeliveryFacade {

    void createDelivery(DeliveryCreateRequestDto deliveryRequestDto);

    String updateStatus(UUID deliveryId, String status);
}
