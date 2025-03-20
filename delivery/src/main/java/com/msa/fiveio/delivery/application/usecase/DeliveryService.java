package com.msa.fiveio.delivery.application.usecase;

import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;
import com.msa.fiveio.delivery.presentation.dto.request.DeliverySearchRequestDto;
import com.msa.fiveio.delivery.presentation.dto.response.DeliveryResponseDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryService {

    void createDelivery(DeliveryCreateRequestDto deliveryCreateRequestDto);

    String updateStatus(UUID deliveryId, String status);

    Page<DeliveryResponseDto> readDeliveries(DeliverySearchRequestDto requestDto,
        Pageable pageable);

    DeliveryResponseDto readDelivery(UUID deliveryId);
}
