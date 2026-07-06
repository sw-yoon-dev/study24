package com.study24.api.domain.product;

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
 * 판매 상품(이용권) 엔티티. 예: "1개월 정기권", "100시간 이용권" 등 상품 정의를 담당한다.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false)
	private int price;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private ProductType type;

	/** 기간제(PERIOD) 상품일 때 사용하는 이용 기간(일) */
	@Column(name = "period_days")
	private Integer periodDays;

	/** 시간제(TIME) 상품일 때 사용하는 총 이용 시간(시간) */
	@Column(name = "total_hours")
	private Integer totalHours;

	@Column(nullable = false)
	private boolean active;

	@Builder
	public Product(String name, int price, ProductType type, Integer periodDays, Integer totalHours) {
		this.name = name;
		this.price = price;
		this.type = type;
		this.periodDays = periodDays;
		this.totalHours = totalHours;
		this.active = true;
	}

	/**
	 * 상품 판매를 중단한다.
	 */
	public void deactivate() {
		this.active = false;
	}
}
