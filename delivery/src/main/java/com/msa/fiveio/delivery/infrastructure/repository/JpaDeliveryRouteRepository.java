package com.msa.fiveio.delivery.infrastructure.repository;

import com.msa.fiveio.delivery.model.entity.DeliveryRoute;
import com.msa.fiveio.delivery.model.repository.DeliveryRouteRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaDeliveryRouteRepository extends DeliveryRouteRepository,
    JpaRepository<DeliveryRoute, UUID> {

}
