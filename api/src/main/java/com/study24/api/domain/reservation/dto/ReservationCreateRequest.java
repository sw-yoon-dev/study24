package com.study24.api.domain.reservation.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

/**
 * 좌석 예약 생성 요청 DTO
 */
public record ReservationCreateRequest(
	@NotNull(message = "회원 ID는 필수입니다.") Long memberId,
	@NotNull(message = "좌석 ID는 필수입니다.") Long seatId,
	LocalDateTime startTime
) {
}
