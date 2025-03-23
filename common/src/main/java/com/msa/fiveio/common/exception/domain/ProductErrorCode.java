package com.msa.fiveio.common.exception.domain;

import com.msa.fiveio.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ProductErrorCode implements ErrorCode {
    PRODUCT_NOT_FOUND("PRODUCT_404", "존재하지 않는 상품입니다.", HttpStatus.NOT_FOUND),
    NEGATIVE_INVENTORY_ERROR("PRODUCT_40O", "재고가 음수입니다.", HttpStatus.BAD_REQUEST),

    STOCK_NOT_FOUND("STOCK_404", "재고정보가 존재하지 않습니다", HttpStatus.NOT_FOUND);

    //상품-허브id 가져오기 실패
    //상품-company 요청 실패


    private final String code;
    private final String message;
    private final HttpStatus status;
}
