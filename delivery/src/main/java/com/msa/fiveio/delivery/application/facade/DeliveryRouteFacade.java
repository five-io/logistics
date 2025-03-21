package com.msa.fiveio.delivery.application.facade;

import com.msa.fiveio.delivery.presentation.dto.request.CreateDeliveryRouteRequest;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryRouteFacade {

    void createDelRoute(CreateDeliveryRouteRequest request);
}
