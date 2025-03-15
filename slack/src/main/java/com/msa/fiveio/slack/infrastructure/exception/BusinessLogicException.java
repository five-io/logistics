package com.msa.fiveio.slack.infrastructure.exception;

public class BusinessLogicException extends RuntimeException {
	public BusinessLogicException(String message) {
		super(message);
	}
}
