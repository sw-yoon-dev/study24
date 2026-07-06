package com.study24.api.domain.payment;

/**
 * 결제/이용권 상태
 */
public enum PaymentStatus {

	/** 사용 가능(잔여 시간/기간 있음) */
	ACTIVE,
	/** 만료됨 */
	EXPIRED,
	/** 결제 취소/환불 */
	CANCELLED
}
