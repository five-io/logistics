package com.msa.fiveio.delivery.application.usecase;

import com.msa.fiveio.delivery.model.entity.enums.DeliveryRouteStatus;
import com.msa.fiveio.delivery.presentation.dto.DeliveryRouteResponse;
import com.msa.fiveio.delivery.presentation.dto.request.CreateDeliveryRouteRequest;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryRouteService {

    void createDelRoute(CreateDeliveryRouteRequest request);

    void updateDelRoute(int sequence, UUID deliveryId, DeliveryRouteStatus status);

    List<DeliveryRouteResponse> getRouteListById(UUID deliveryId);
}
