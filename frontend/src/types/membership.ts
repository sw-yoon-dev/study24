/** 좌석 카테고리. 백엔드 Seat.SeatType(GENERAL/FIXED)에 대응 */
export type SeatCategory = 'FREE' | 'FIXED'

/** 하나의 이용권 카드에 표시할 정보 */
export interface MembershipTicket {
  id: string
  /** 입실/퇴실 배지 표시 여부. 백엔드 Reservation.status(IN_USE 여부)에 대응 */
  isCheckedIn: boolean
  seatCategory: SeatCategory
  /** "시간권 1550시간" 같은 표시용 라벨 */
  ticketLabel: string
  /** 유효기간 시작 (ISO) */
  validFrom: string
  /** 유효기간 종료 (ISO) */
  validTo: string
  /** 남은 이용시간(초). 백엔드 Payment.remainingMinutes 를 초 단위로 환산해 카운트다운에 사용 */
  remainingSeconds: number
  /** 일일 무료 외출 잔여시간(초) */
  dailyFreeGoOutSeconds: number
}

/** 상단 헤더에 표시할 회원 정보 */
export interface MemberInfo {
  displayName: string
  operatingStatusLabel: string
  operatingHoursLabel: string
}
