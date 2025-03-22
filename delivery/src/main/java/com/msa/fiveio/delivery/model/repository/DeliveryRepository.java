package com.msa.fiveio.delivery.model.repository;

import com.msa.fiveio.delivery.model.entity.Delivery;
import com.msa.fiveio.delivery.presentation.dto.request.DeliverySearchRequestDto;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryRepository {

    Delivery save(Delivery delivery);

    Optional<Delivery> findById(UUID id);

    Page<Delivery> readDeliveries(DeliverySearchRequestDto requestDto, Pageable pageable);

    Optional<Delivery> findByOrderId(UUID orderId);
}
