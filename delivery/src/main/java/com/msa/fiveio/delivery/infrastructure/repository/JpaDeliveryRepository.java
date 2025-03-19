package com.msa.fiveio.delivery.infrastructure.repository;

import com.msa.fiveio.delivery.model.entity.Delivery;
import com.msa.fiveio.delivery.model.repository.DeliveryRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDeliveryRepository extends DeliveryRepository, JpaRepository<Delivery, UUID> {

}
