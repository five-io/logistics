package com.msa.fiveio.common.exception.domain;

import com.msa.fiveio.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    AUTH_NOT_FOUND("TOKEN_404", "토큰이 없습니다.", HttpStatus.NOT_FOUND),
    AUTH_UNAUTHORIZED("AUTH_401", "권한이 만료되었습니다.", HttpStatus.UNAUTHORIZED);

    private final String code;
    private final String message;
    private final HttpStatus status;

}
