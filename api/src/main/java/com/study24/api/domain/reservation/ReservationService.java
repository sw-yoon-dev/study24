package com.study24.api.domain.reservation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study24.api.domain.member.Member;
import com.study24.api.domain.member.MemberRepository;
import com.study24.api.domain.seat.Seat;
import com.study24.api.domain.seat.SeatRepository;
import com.study24.api.domain.seat.SeatStatus;
import com.study24.api.global.exception.BusinessException;
import com.study24.api.global.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

/**
 * 좌석 예약 생성/취소를 처리하는 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

	/** 예약 생성 시 활성 상태로 취급할 예약 상태 목록(중복 예약 방어 검증용) */
	private static final List<ReservationStatus> ACTIVE_STATUSES =
		List.of(ReservationStatus.RESERVED, ReservationStatus.IN_USE);

	private final ReservationRepository reservationRepository;
	private final SeatRepository seatRepository;
	private final MemberRepository memberRepository;

	/**
	 * 좌석을 예약한다.
	 * 좌석 로우에 비관적 락을 걸어 동시 요청 시 하나의 예약만 성공하도록 보장한다.
	 */
	public Reservation reserve(Long memberId, Long seatId, LocalDateTime startTime) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

		// SELECT ... FOR UPDATE : 같은 좌석에 대한 동시 예약 요청을 DB 로우 락으로 직렬화한다.
		Seat seat = seatRepository.findByIdForUpdate(seatId)
			.orElseThrow(() -> new BusinessException(ErrorCode.SEAT_NOT_FOUND));

		if (seat.getStatus() != SeatStatus.AVAILABLE) {
			throw new BusinessException(ErrorCode.SEAT_ALREADY_OCCUPIED);
		}

		// 데이터 정합성에 대한 방어적 이중 검증(락 획득 후이므로 정상 흐름에서는 항상 false)
		if (reservationRepository.existsBySeatIdAndStatusIn(seatId, ACTIVE_STATUSES)) {
			throw new BusinessException(ErrorCode.SEAT_ALREADY_OCCUPIED);
		}

		seat.occupy();

		Reservation reservation = Reservation.builder()
			.member(member)
			.seat(seat)
			.payment(null)
			.startTime(startTime != null ? startTime : LocalDateTime.now())
			.build();

		return reservationRepository.save(reservation);
	}

	/**
	 * 예약을 취소하고 좌석을 다시 이용 가능 상태로 되돌린다.
	 */
	public void cancel(Long reservationId, Long memberId) {
		Reservation reservation = reservationRepository.findById(reservationId)
			.orElseThrow(() -> new BusinessException(ErrorCode.RESERVATION_NOT_FOUND));

		if (!reservation.getMember().getId().equals(memberId)) {
			throw new BusinessException(ErrorCode.RESERVATION_ACCESS_DENIED);
		}

		reservation.cancel();
		reservation.getSeat().release();
	}

	/**
	 * 특정 회원의 예약 목록을 조회한다.
	 */
	@Transactional(readOnly = true)
	public List<Reservation> findMyReservations(Long memberId) {
		return reservationRepository.findByMemberIdWithSeatAndMember(memberId);
	}
}
