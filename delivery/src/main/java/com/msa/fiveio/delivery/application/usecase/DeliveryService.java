package com.msa.fiveio.delivery.application.usecase;

import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;
import java.util.UUID;

public interface DeliveryService {

    void createDelivery(DeliveryCreateRequestDto deliveryCreateRequestDto);

    String updateStatus(UUID deliveryId, String status);
}
