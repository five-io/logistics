package com.msa.fiveio.delivery.application.facade;

import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;

public interface DeliveryFacade {

    void createDelivery(DeliveryCreateRequestDto deliveryRequestDto);

}
