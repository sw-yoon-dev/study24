package com.study24.api.domain.seat;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study24.api.domain.seat.dto.SeatResponse;
import com.study24.api.global.response.ApiResponse;

import lombok.RequiredArgsConstructor;

/**
 * 좌석 조회 API
 */
@RestController
@RequiredArgsConstructor
public class SeatController {

	private final SeatQueryService seatQueryService;

	/**
	 * 이용 가능한(빈) 좌석 목록을 조회한다. 좌석 타입으로 선택적으로 필터링할 수 있다.
	 */
	@GetMapping("/api/seats/available")
	public ApiResponse<List<SeatResponse>> getAvailableSeats(
		@RequestParam(required = false) SeatType seatType) {
		List<SeatResponse> result = seatQueryService.findAvailableSeats(seatType).stream()
			.map(SeatResponse::from)
			.toList();
		return ApiResponse.success(result);
	}

	/**
	 * 전체 좌석 목록을 조회한다.
	 */
	@GetMapping("/api/seats")
	public ApiResponse<List<SeatResponse>> getAllSeats() {
		List<SeatResponse> result = seatQueryService.findAllSeats().stream()
			.map(SeatResponse::from)
			.toList();
		return ApiResponse.success(result);
	}
}
