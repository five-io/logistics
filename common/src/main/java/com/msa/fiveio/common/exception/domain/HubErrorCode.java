package com.msa.fiveio.common.exception.domain;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.msa.fiveio.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HubErrorCode implements ErrorCode {
    HUBS_NOT_FOUND("HUB_404", "존재하지 않는 허브입니다.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
