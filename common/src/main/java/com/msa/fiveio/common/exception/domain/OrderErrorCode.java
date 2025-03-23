package com.msa.fiveio.common.exception.domain;

import com.msa.fiveio.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OrderErrorCode implements ErrorCode {
    DELIVERY_REQUEST_FAILED("ORDER_001", "배송 요청 중 오류가 발생했습니다.", HttpStatus.SERVICE_UNAVAILABLE),
    DELIVERY_STATUS_FETCH_FAILED("ORDER_002", "배송 상태 조회에 실패했습니다.", HttpStatus.BAD_GATEWAY),
    PRODUCT_REQUEST_FAILED("ORDER_003", "상품 요청 중 오류가 발생했습니다.", HttpStatus.SERVICE_UNAVAILABLE),
    STOCK_ROLLBACK_FAILED("ORDER_004", "재고 롤백 요청이 실패했습니다.", HttpStatus.SERVICE_UNAVAILABLE),
    INVALID_QUANTITY("ORDER_005", "상품 개수는 1개부터 주문할 수 있습니다.", HttpStatus.BAD_GATEWAY),
    INVALID_ORDER_STATUS("ORDER_006", "주문의 상태가 올바르지 않습니다.", HttpStatus.BAD_REQUEST),
    DELIVERY_DELETE_FAILED("ORDER_007", "배송 삭제 요청 중 오류가 발생했습니다.", HttpStatus.SERVICE_UNAVAILABLE);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
