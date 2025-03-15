package com.msa.fiveio.common.exception;

import java.nio.file.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    // CustomException 발생 시 처리
    @ExceptionHandler(CustomException.class)
    public ErrorResponse handleCustomException(CustomException e) {
        return new ErrorResponse(e.getCode(), e.getMessage(), e.getHttpStatus());
    }

    // 잘못된 권한 예외 처리
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponse handleAccessDeniedException(
        AccessDeniedException e) {
        return new ErrorResponse("ACCESS_DENIED", "접근이 거부된 권한입니다.",
                HttpStatus.FORBIDDEN.value());
    }



}
