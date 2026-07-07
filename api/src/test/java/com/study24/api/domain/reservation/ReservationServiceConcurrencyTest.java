package com.study24.api.domain.reservation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study24.api.domain.member.Member;
import com.study24.api.domain.member.MemberRepository;
import com.study24.api.domain.seat.Seat;
import com.study24.api.domain.seat.SeatRepository;
import com.study24.api.domain.seat.SeatStatus;
import com.study24.api.domain.seat.SeatType;
import com.study24.api.global.exception.BusinessException;
import com.study24.api.global.exception.ErrorCode;
import org.springframework.test.annotation.Rollback;

/**
 * 동일 좌석에 대한 동시 예약 요청이 하나만 성공하는지 검증하는 테스트
 */
//@Rollback(false)
@SpringBootTest
class ReservationServiceConcurrencyTest {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	void 동시에_같은_좌석을_예약하면_하나만_성공한다() throws InterruptedException {
		// 롤백 없이(@Rollback(false)) 반복 실행해도 seat_number unique 제약에 걸리지 않도록 매번 고유한 좌석 번호를 생성한다.
		String seatNumber = "TEST-" + System.currentTimeMillis();
		Seat seat = seatRepository.save(Seat.builder()
			.seatNumber(seatNumber)
			.seatType(SeatType.GENERAL)
			.status(SeatStatus.AVAILABLE)
			.build());

		int threadCount = 10;
		List<Member> members = createMembers(threadCount);

		// ExecuterService 스레드 풀로, 가상의 사용자 10명이 동시에 오는 환경을 만든다.
		// Executors.newFixedThreadPool(10)을 통해 동시에 작업할 수 있는 10개의 쓰레드를 생성
		ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
		// 쓰레드들은 각자 비동기적으로 실행되기 때문에 언제 끝날지 모른다. 따라서 .await()를 통해 10명이 모두 예약을 시도하고 끝날 때까지 기다린다.
		CountDownLatch latch = new CountDownLatch(threadCount);
		//여러 쓰레드가 동시에 int 변수에 ++ 연산을 하면 동시성 이슈(값이 씹힘)가 발생한다.
		//AtomicInteger는 멀티쓰레드 환경에서도 안전하게 숫자를 세어주는 객체다.
		AtomicInteger successCount = new AtomicInteger();
		AtomicInteger failCount = new AtomicInteger();

		for (Member member : members) {
			// 10개의 쓰레드가 아래의 로직을 동시에 실행
			executorService.submit(() -> {
				try {
					reservationService.reserve(member.getId(), seat.getId(), null);
					successCount.incrementAndGet();
				} catch (BusinessException e) {
					// 이미 누가 예약해서 비즈니스 예외가 터지면 실패 카운트
					if (e.getErrorCode() == ErrorCode.SEAT_ALREADY_OCCUPIED) {
						failCount.incrementAndGet();
					}
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await(10, TimeUnit.SECONDS);
		executorService.shutdown();

		assertThat(successCount.get()).isEqualTo(1);
		assertThat(failCount.get()).isEqualTo(threadCount - 1);

		Seat occupiedSeat = seatRepository.findById(seat.getId()).orElseThrow();
		assertThat(occupiedSeat.getStatus()).isEqualTo(SeatStatus.OCCUPIED);
	}

	private List<Member> createMembers(int count) {
		// 롤백 없이(@Rollback(false)) 반복 실행해도 email unique 제약에 걸리지 않도록 매번 고유한 접두사를 붙인다.
		String prefix = System.currentTimeMillis() + "-";
		// for( int i = 0; i < count; i++) 루프을 스트림으로 표현한 것이다.
		return java.util.stream.IntStream.range(0, count)
			.mapToObj(i -> memberRepository.save(Member.builder() // 앞에 만든 숫자를 받아 member 객체를 만들어서 저장한다.
				.email(prefix + i + "@test.com")
				.password("password")
				.name("회원" + i)
				.build()))
			.toList();
	}
}
