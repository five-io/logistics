package com.msa.fiveio.delivery.model.repository;

import com.msa.fiveio.delivery.model.entity.Delivery;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepository {

    Delivery save(Delivery delivery);

    Optional<Delivery> findById(UUID id);
}
