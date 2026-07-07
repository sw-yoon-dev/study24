package com.study24.api.domain.reservation.dto;

import java.time.LocalDateTime;

import com.study24.api.domain.reservation.Reservation;
import com.study24.api.domain.reservation.ReservationStatus;

/**
 * 예약 정보 응답 DTO
 */
public record ReservationResponse(
	Long reservationId,
	Long seatId,
	String seatNumber,
	Long memberId,
	LocalDateTime startTime,
	LocalDateTime endTime,
	ReservationStatus status
) {

	/**
	 * 예약 엔티티로부터 응답 DTO를 생성한다.
	 */
	public static ReservationResponse from(Reservation reservation) {
		return new ReservationResponse(
			reservation.getId(),
			reservation.getSeat().getId(),
			reservation.getSeat().getSeatNumber(),
			reservation.getMember().getId(),
			reservation.getStartTime(),
			reservation.getEndTime(),
			reservation.getStatus());
	}
}
