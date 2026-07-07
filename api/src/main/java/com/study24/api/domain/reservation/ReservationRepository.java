package com.study24.api.domain.reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	/**
	 * 특정 좌석에 대해 주어진 상태들 중 하나인 예약이 존재하는지 확인한다.
	 * 동시성 제어의 1차 방어는 비관적 락이며, 이 메서드는 데이터 정합성에 대한 방어적 이중 검증용이다.
	 */
	boolean existsBySeatIdAndStatusIn(Long seatId, List<ReservationStatus> statuses);

	/**
	 * 특정 회원의 예약 목록을 좌석 정보와 함께(N+1 방지) 최신순으로 조회한다.
	 */
	@Query("select r from Reservation r join fetch r.seat join fetch r.member where r.member.id = :memberId order by r.id desc")
	List<Reservation> findByMemberIdWithSeatAndMember(@Param("memberId") Long memberId);
}
