package com.study24.api.global.exception;

import lombok.Getter;

/**
 * 비즈니스 규칙 위반 시 발생시키는 공통 예외
 */
@Getter
public class BusinessException extends RuntimeException {

	private final ErrorCode errorCode;

	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
