package com.msa.fiveio.common.exception.domain;

import com.msa.fiveio.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SlackErrorCode implements ErrorCode {
	SLACKS_NOT_FOUND("SLACK_404", "존재하지 않는 슬랙입니다", HttpStatus.NOT_FOUND);

	private final String code;
	private final String message;
	private final HttpStatus status;
}
