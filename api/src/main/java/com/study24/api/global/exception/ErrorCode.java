package com.study24.api.global.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 비즈니스 예외에서 사용하는 에러 코드 정의
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
	SEAT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 좌석입니다."),
	RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 예약입니다."),
	SEAT_ALREADY_OCCUPIED(HttpStatus.CONFLICT, "이미 사용 중이거나 예약된 좌석입니다."),
	RESERVATION_ACCESS_DENIED(HttpStatus.FORBIDDEN, "본인의 예약만 취소할 수 있습니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
