package com.study24.api.domain.seat;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.LockModeType;

public interface SeatRepository extends JpaRepository<Seat, Long> {

	/**
	 * 특정 상태의 좌석 목록을 조회한다.
	 */
	List<Seat> findByStatus(SeatStatus status);

	/**
	 * 특정 상태와 타입을 모두 만족하는 좌석 목록을 조회한다.
	 */
	List<Seat> findByStatusAndSeatType(SeatStatus status, SeatType seatType);

	/**
	 * 예약 생성 시 동시성 제어를 위해 좌석 로우에 쓰기 락을 걸고 조회한다.
	 * 동일 좌석에 대한 동시 예약 요청을 DB 로우 락으로 직렬화한다.
	 */
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select s from Seat s where s.id = :seatId")
	Optional<Seat> findByIdForUpdate(@Param("seatId") Long seatId);
}
