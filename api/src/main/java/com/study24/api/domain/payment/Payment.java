package com.study24.api.domain.payment;

import java.time.LocalDateTime;

import com.study24.api.domain.member.Member;
import com.study24.api.domain.product.Product;
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
 * 결제 및 이용권 보유 내역 엔티티.
 * 회원이 특정 상품을 구매한 기록이자, 잔여 이용 시간/만료일을 관리하는 이용권 역할을 겸한다.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(nullable = false)
	private int amount;

	@Column(name = "paid_at", nullable = false)
	private LocalDateTime paidAt;

	/** 잔여 이용 시간(분). 시간제 상품에서 사용 */
	@Column(name = "remaining_minutes")
	private Integer remainingMinutes;

	/** 이용권 만료일. 기간제 상품에서 사용 */
	@Column(name = "expired_at")
	private LocalDateTime expiredAt;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private PaymentStatus status;

	@Builder
	public Payment(Member member, Product product, int amount, Integer remainingMinutes, LocalDateTime expiredAt) {
		this.member = member;
		this.product = product;
		this.amount = amount;
		this.paidAt = LocalDateTime.now();
		this.remainingMinutes = remainingMinutes;
		this.expiredAt = expiredAt;
		this.status = PaymentStatus.ACTIVE;
	}

	/**
	 * 좌석 이용 등으로 잔여 이용 시간을 차감한다.
	 */
	public void useMinutes(int minutes) {
		if (this.remainingMinutes != null) {
			this.remainingMinutes = Math.max(0, this.remainingMinutes - minutes);
		}
	}

	/**
	 * 결제를 취소(환불) 처리한다.
	 */
	public void cancel() {
		this.status = PaymentStatus.CANCELLED;
	}

	/**
	 * 이용권을 만료 처리한다.
	 */
	public void expire() {
		this.status = PaymentStatus.EXPIRED;
	}
}
