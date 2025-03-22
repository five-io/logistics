package com.msa.fiveio.delivery.model.repository;

import com.msa.fiveio.delivery.model.entity.DeliveryRoute;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;

public interface DeliveryRouteRepository {

    List<DeliveryRoute> findByDelivery_Id(UUID deliveryId);

    @Query("SELECT dr FROM DeliveryRoute dr WHERE dr.deliveryRouteDetails.sequence = :sequence AND dr.delivery.id = :deliveryId")
    DeliveryRoute findBySequenceAndDeliveryId(int sequence, UUID deliveryId);
}
