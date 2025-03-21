package com.msa.fiveio.delivery.application.facade;

import com.msa.fiveio.delivery.application.usecase.DeliveryRouteService;
import com.msa.fiveio.delivery.presentation.dto.request.CreateDeliveryRouteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryRouteFacadeImpl implements DeliveryRouteFacade {

    private final DeliveryRouteService deliveryRouteService;

    @Override
    public void createDelRoute(CreateDeliveryRouteRequest request) {
        deliveryRouteService.createDelRoute(request);
    }
}
