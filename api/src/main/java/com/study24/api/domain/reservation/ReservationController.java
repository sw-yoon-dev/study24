package com.study24.api.domain.reservation;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study24.api.domain.reservation.dto.ReservationCreateRequest;
import com.study24.api.domain.reservation.dto.ReservationResponse;
import com.study24.api.global.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * 좌석 예약 API
 */
@RestController
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;

	/**
	 * 좌석을 예약한다.
	 */
	@PostMapping("/api/reservations")
	public ApiResponse<ReservationResponse> reserve(@Valid @RequestBody ReservationCreateRequest request) {
		Reservation reservation = reservationService.reserve(
			request.memberId(), request.seatId(), request.startTime());
		return ApiResponse.success(ReservationResponse.from(reservation));
	}

	/**
	 * 예약을 취소한다.
	 */
	@DeleteMapping("/api/reservations/{reservationId}")
	public ApiResponse<Void> cancel(
		@PathVariable Long reservationId,
		@RequestParam Long memberId) {
		reservationService.cancel(reservationId, memberId);
		return ApiResponse.success(null);
	}

	/**
	 * 특정 회원의 예약 목록을 조회한다.
	 */
	@GetMapping("/api/reservations")
	public ApiResponse<List<ReservationResponse>> getMyReservations(@RequestParam Long memberId) {
		List<ReservationResponse> result = reservationService.findMyReservations(memberId).stream()
			.map(ReservationResponse::from)
			.toList();
		return ApiResponse.success(result);
	}
}
