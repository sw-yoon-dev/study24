package com.study24.api.domain.seat;

import com.study24.api.global.entity.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 좌석 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seat extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seat_id")
	private Long id;

	@Column(name = "seat_number", nullable = false, unique = true, length = 20)
	private String seatNumber;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private SeatStatus status;

	@Builder
	public Seat(String seatNumber, SeatStatus status) {
		this.seatNumber = seatNumber;
		this.status = status != null ? status : SeatStatus.AVAILABLE;
	}

	/**
	 * 좌석을 사용 중 상태로 변경한다.
	 */
	public void occupy() {
		this.status = SeatStatus.OCCUPIED;
	}

	/**
	 * 좌석을 이용 가능 상태로 되돌린다.
	 */
	public void release() {
		this.status = SeatStatus.AVAILABLE;
	}
}
