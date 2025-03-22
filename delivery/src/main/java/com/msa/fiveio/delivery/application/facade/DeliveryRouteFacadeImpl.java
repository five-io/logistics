package com.msa.fiveio.delivery.application.facade;

import com.msa.fiveio.delivery.application.usecase.DeliveryRouteService;
import com.msa.fiveio.delivery.application.usecase.DeliveryService;
import com.msa.fiveio.delivery.model.entity.enums.DeliveryRouteStatus;
import com.msa.fiveio.delivery.presentation.dto.DeliveryRouteResponse;
import com.msa.fiveio.delivery.presentation.dto.request.CreateDeliveryRouteRequest;
import com.msa.fiveio.delivery.presentation.dto.request.DeliveryRouteRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryRouteFacadeImpl implements DeliveryRouteFacade {

    private final DeliveryRouteService deliveryRouteService;
    private final DeliveryService deliveryService;

    @Override
    public void createDelRoute(CreateDeliveryRouteRequest request) {
        deliveryRouteService.createDelRoute(request);
    }

    @Override
    public void updateDelRoute(DeliveryRouteRequest request) {
        //배송경로 시퀀스 확인
        List<DeliveryRouteResponse> responseList = deliveryRouteService.getRouteListById(
            request.deliveryId());
        Boolean flag = true;

        for (int i = 0; i < responseList.size(); i++) {
            //도착
            DeliveryRouteResponse response = responseList.get(i);
            if (response.getDeliveryRouteDetails().getArriveHubId()
                .equals(request.arrviedHubId())) {
                if (i != responseList.size()) {
                    //다음 배송중
                    DeliveryRouteResponse nextRoute = responseList.get(i + 1);
                    deliveryRouteService.updateDelRoute(
                        nextRoute.getDeliveryRouteDetails().getSequence(),
                        nextRoute.getDelivery().getId(),
                        DeliveryRouteStatus.IN_DELIVERY);
                    flag = false;
                }
                //현재 배송완료
                deliveryRouteService.updateDelRoute(
                    response.getDeliveryRouteDetails().getSequence(),
                    response.getDelivery().getId(),
                    DeliveryRouteStatus.ARRIVED_AT_DESTINATION_HUB);
            }
        }
        if (flag) {
            //배송update
            deliveryService.updateStatus(request.deliveryId(), "ARRIVED_AT_DESTINATION_HUB");
        }

    }


}
