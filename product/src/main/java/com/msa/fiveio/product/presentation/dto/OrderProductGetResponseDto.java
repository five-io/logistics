package com.msa.fiveio.product.presentation.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderProductGetResponseDto {

    private String deliveryAddress;
    private UUID requesterCompanyId;
    private UUID departHubId;
    private UUID arriveHubId;
    private double productPrice;
    //todo. 재고확인 결과 errorcode 추가
}
