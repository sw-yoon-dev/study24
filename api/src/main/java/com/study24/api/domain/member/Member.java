package com.study24.api.domain.member;

import com.study24.api.global.entity.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Column(nullable = false, unique = true, length = 100)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, length = 50)
	private String name;

	@Builder
	public Member(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}

	/**
	 * 비밀번호를 변경한다.
	 */
	public void changePassword(String newPassword) {
		this.password = newPassword;
	}

	/**
	 * 회원 이름을 변경한다.
	 */
	public void changeName(String newName) {
		this.name = newName;
	}
}
