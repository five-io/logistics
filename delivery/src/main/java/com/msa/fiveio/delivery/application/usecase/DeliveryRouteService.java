package com.msa.fiveio.delivery.application.usecase;

import com.msa.fiveio.delivery.presentation.dto.request.CreateDeliveryRouteRequest;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryRouteService {

    void createDelRoute(CreateDeliveryRouteRequest request);
}
