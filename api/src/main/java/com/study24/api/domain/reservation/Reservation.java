package com.study24.api.domain.reservation;

import java.time.LocalDateTime;

import com.study24.api.domain.member.Member;
import com.study24.api.domain.payment.Payment;
import com.study24.api.domain.seat.Seat;
import com.study24.api.global.entity.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 좌석 예약/이용 내역 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seat_id", nullable = false)
	private Seat seat;

	/** 이 예약에 사용된 이용권. 이용권 연동 기능은 이번 범위 밖이라 null로 생성될 수 있다. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_id", nullable = true)
	private Payment payment;

	@Column(name = "start_time", nullable = false)
	private LocalDateTime startTime;

	@Column(name = "end_time")
	private LocalDateTime endTime;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private ReservationStatus status;

	@Builder
	public Reservation(Member member, Seat seat, Payment payment, LocalDateTime startTime) {
		this.member = member;
		this.seat = seat;
		this.payment = payment;
		this.startTime = startTime;
		this.status = ReservationStatus.RESERVED;
	}

	/**
	 * 입실 처리(이용 시작)
	 */
	public void startUsing() {
		this.status = ReservationStatus.IN_USE;
	}

	/**
	 * 퇴실 처리(이용 종료)
	 */
	public void complete(LocalDateTime endTime) {
		this.endTime = endTime;
		this.status = ReservationStatus.COMPLETED;
	}

	/**
	 * 예약을 취소한다.
	 */
	public void cancel() {
		this.status = ReservationStatus.CANCELLED;
	}
}
