package com.msa.fiveio.order.infrastructure.client.dto.response;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryCreateResponseDto {

    private final UUID orderId;
    private final UUID deliveryId;
    private final UUID requesterCompanyId;
    private final UUID receiverCompanyId;

}
