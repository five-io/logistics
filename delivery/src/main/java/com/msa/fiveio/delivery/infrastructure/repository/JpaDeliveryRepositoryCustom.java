package com.msa.fiveio.delivery.infrastructure.repository;

import com.msa.fiveio.delivery.model.entity.Delivery;
import com.msa.fiveio.delivery.presentation.dto.request.DeliverySearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JpaDeliveryRepositoryCustom {

    Page<Delivery> readDeliveries(DeliverySearchRequestDto requestDto, Pageable pageable);
}
