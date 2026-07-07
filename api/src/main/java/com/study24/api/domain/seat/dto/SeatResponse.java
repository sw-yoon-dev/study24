package com.study24.api.domain.seat.dto;

import com.study24.api.domain.seat.Seat;
import com.study24.api.domain.seat.SeatStatus;
import com.study24.api.domain.seat.SeatType;

/**
 * 좌석 정보 응답 DTO
 */
public record SeatResponse(Long seatId, String seatNumber, SeatType seatType, SeatStatus status) {

	/**
	 * 좌석 엔티티로부터 응답 DTO를 생성한다.
	 */
	public static SeatResponse from(Seat seat) {
		return new SeatResponse(seat.getId(), seat.getSeatNumber(), seat.getSeatType(), seat.getStatus());
	}
}
