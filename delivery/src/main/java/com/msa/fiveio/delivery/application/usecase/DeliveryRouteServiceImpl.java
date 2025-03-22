package com.msa.fiveio.delivery.application.usecase;

import com.msa.fiveio.delivery.infrastructure.client.dto.response.RouteResponseDto;
import com.msa.fiveio.delivery.infrastructure.repository.JpaDeliveryRouteRepository;
import com.msa.fiveio.delivery.model.entity.Delivery;
import com.msa.fiveio.delivery.model.entity.DeliveryRoute;
import com.msa.fiveio.delivery.model.entity.DeliveryRouteDetails;
import com.msa.fiveio.delivery.model.entity.enums.DeliveryRouteStatus;
import com.msa.fiveio.delivery.model.repository.DeliveryRepository;
import com.msa.fiveio.delivery.presentation.dto.DeliveryRouteResponse;
import com.msa.fiveio.delivery.presentation.dto.request.CreateDeliveryRouteRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryRouteServiceImpl implements DeliveryRouteService {

    private final DeliveryRepository deliveryRepository;
    private final JpaDeliveryRouteRepository deliveryRouteRepository;

    @Transactional
    @Override
    public void createDelRoute(CreateDeliveryRouteRequest request) {
        List<DeliveryRoute> delRouteList = new ArrayList<>();

        Long deliverManId = request.getDeliveryManager();
        UUID deliverId = request.getDelivery().getDeliveryId();
        List<RouteResponseDto> routes = request.getRoutes();

        Delivery delivery = deliveryRepository.findById(deliverId).get();

        int count = 1;
        for (RouteResponseDto dto : routes) {
            DeliveryRouteDetails routeDetails = DeliveryRouteDetails.builder()
                .sequence(count)
                .departHubId(dto.departId())
                .arriveHubId(dto.arriveId())
                .estimatedDistanceKm(dto.distance())
                .estimatedDurationMin(dto.takenTime())
                .build();

            DeliveryRoute route = DeliveryRoute.builder()
                .delivery(delivery)
                .deliveryRouteDetails(routeDetails)
                .actualDistanceKm(null)
                .actualDurationMin(null)
                .deliveryRouteStatus(DeliveryRouteStatus.IN_DELIVERY)
                .deliveryManager(deliverManId)
                .build();

            count++;
            delRouteList.add(route);
        }

        deliveryRouteRepository.saveAll(delRouteList);

    }

    @Transactional
    @Override
    public void updateDelRoute(int sequence, UUID deliveryId, DeliveryRouteStatus status) {
        DeliveryRoute deliveryRoute = deliveryRouteRepository.findBySequenceAndDeliveryId(sequence,
            deliveryId);

        deliveryRoute.updateStatus(status);
    }

    @Override
    public List<DeliveryRouteResponse> getRouteListById(UUID deliveryId) {
        List<DeliveryRoute> deliveryRoutes = deliveryRouteRepository.findByDelivery_Id(deliveryId);
        List<DeliveryRouteResponse> responses = deliveryRoutes.stream()
            .sorted(Comparator.comparing(route -> route.getDeliveryRouteDetails().getSequence()))
            .map(DeliveryRouteResponse::from)
            .collect(Collectors.toList());
        return responses;
    }


}
