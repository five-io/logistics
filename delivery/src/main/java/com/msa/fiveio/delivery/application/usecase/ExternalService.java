package com.msa.fiveio.delivery.application.usecase;

import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;

public interface ExternalService {

    void sendSlackRequest(DeliveryCreateRequestDto deliveryCreateRequestDto);
}
