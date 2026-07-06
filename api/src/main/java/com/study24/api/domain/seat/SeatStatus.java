package com.study24.api.domain.seat;

/**
 * 좌석 상태
 */
public enum SeatStatus {

	/** 이용 가능 */
	AVAILABLE,
	/** 사용 중 */
	OCCUPIED,
	/** 점검 등으로 사용 불가 */
	DISABLED
}
