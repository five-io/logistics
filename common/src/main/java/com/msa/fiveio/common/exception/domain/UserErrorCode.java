package com.msa.fiveio.common.exception.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.msa.fiveio.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    USER_ERROR_CODE("USER_404", "존재하지 않는 유저입니다.", HttpStatus.NOT_FOUND),
    ENUM_ERROR_CODE("ENUM_404", "존재하지 않는 권한입니다.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
