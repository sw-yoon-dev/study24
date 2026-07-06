package com.study24.api.domain.reservation;

/**
 * 예약/이용 상태
 */
public enum ReservationStatus {

	/** 예약중(입실 전) */
	RESERVED,
	/** 이용중(입실 완료) */
	IN_USE,
	/** 이용 완료(퇴실) */
	COMPLETED,
	/** 예약 취소 */
	CANCELLED
}
