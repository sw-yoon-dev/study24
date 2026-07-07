package com.study24.api.domain.seat;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
 * 좌석 조회 전용 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SeatQueryService {

	private final SeatRepository seatRepository;

	/**
	 * 전체 좌석 목록을 조회한다.
	 */
	public List<Seat> findAllSeats() {
		return seatRepository.findAll();
	}

	/**
	 * 이용 가능한(빈) 좌석 목록을 조회한다. 좌석 타입이 주어지면 타입으로도 필터링한다.
	 */
	public List<Seat> findAvailableSeats(SeatType seatType) {
		if (seatType == null) {
			return seatRepository.findByStatus(SeatStatus.AVAILABLE);
		}
		return seatRepository.findByStatusAndSeatType(SeatStatus.AVAILABLE, seatType);
	}
}
