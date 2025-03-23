package com.msa.fiveio.common.exception.domain;

import com.msa.fiveio.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DeliveryErrorCode implements ErrorCode {
    DELIVERY_NOT_FOUND("DELIVERY_001", "해당 배송 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INVALID_DELIVERY_STATUS("DELIVERY_002", "완료된 배송이 아닙니다.", HttpStatus.BAD_REQUEST),
    SLACK_REQUEST_FAILED("DELIVERY_003", "Slack 요청이 실패했습니다.", HttpStatus.SERVICE_UNAVAILABLE),
    CANCEL_INVALID_DELIVERY_STATUS("DELIVERY_004", "이미 발송된 배송입니다.", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
