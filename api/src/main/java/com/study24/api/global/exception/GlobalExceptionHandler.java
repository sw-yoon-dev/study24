package com.study24.api.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.study24.api.global.response.ApiResponse;

/**
 * 컨트롤러 전역에서 발생하는 예외를 공통 응답 포맷으로 변환하는 핸들러
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 비즈니스 규칙 위반 예외를 처리한다.
	 */
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e) {
		return ResponseEntity.status(e.getErrorCode().getHttpStatus())
			.body(ApiResponse.fail(e.getErrorCode().name(), e.getMessage()));
	}

	/**
	 * 요청 값 검증 실패 예외를 처리한다.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
		String message = e.getBindingResult().getFieldErrors().stream()
			.findFirst()
			.map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
			.orElse("잘못된 요청입니다.");
		return ResponseEntity.badRequest().body(ApiResponse.fail("INVALID_REQUEST", message));
	}
}
