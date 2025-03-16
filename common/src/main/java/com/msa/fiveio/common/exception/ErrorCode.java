package com.msa.fiveio.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String getCode();
    String getMessage();
    HttpStatus getStatus();

    default int getHttpStatus() {
        return getStatus().value();
    }

}
