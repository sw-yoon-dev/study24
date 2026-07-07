package com.study24.api.global.response;

/**
 * 모든 API 응답을 감싸는 공통 응답 포맷
 */
public record ApiResponse<T>(boolean success, T data, String errorCode, String message) {

	/**
	 * 성공 응답을 생성한다.
	 */
	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(true, data, null, null);
	}

	/**
	 * 실패 응답을 생성한다.
	 */
	public static <T> ApiResponse<T> fail(String errorCode, String message) {
		return new ApiResponse<>(false, null, errorCode, message);
	}
}
