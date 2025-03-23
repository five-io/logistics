package com.msa.fiveio.user.model.repository;

import com.msa.fiveio.user.model.entity.DeliveryManagers;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryManagerRepository {

  DeliveryManagers save(DeliveryManagers deliveryManagers);

  Optional<DeliveryManagers> findByUserId(Long userId);

  boolean existsById(Long userId);
}