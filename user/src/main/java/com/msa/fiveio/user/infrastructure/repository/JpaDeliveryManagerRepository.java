package com.msa.fiveio.user.infrastructure.repository;

import com.msa.fiveio.user.model.entity.DeliveryManagers;
import com.msa.fiveio.user.model.repository.DeliveryManagerRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDeliveryManagerRepository extends DeliveryManagerRepository, JpaRepository<DeliveryManagers, Long> {
}
