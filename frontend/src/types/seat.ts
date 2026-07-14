import type { SeatCategory } from './membership'

/** 배치도에 표시되는 좌석의 화면 상태 (백엔드 SeatStatus를 UI 표시용으로 세분화) */
export type SeatDisplayStatus =
  | 'AVAILABLE' // 선택가능 - 빈 흰 원
  | 'UNAVAILABLE' // 선택불가 - 회색 원
  | 'LEAVING_SOON' // 퇴실예정석 - 체크 아이콘
  | 'FIXED_OCCUPIED' // 고정석 - 별 아이콘

/** 배치도 상 좌석의 시각적 그룹 (좌표 생성/스타일 분기용) */
export type SeatLayoutGroup =
  | 'LEFT_COLUMN' // 좌측 세로 1열 (1G~8G)
  | 'TOP_ROW' // 최상단 가로 1열 (9G~17G)
  | 'STUDY_ROOM_PAIR' // 스터디룸 옆 마주보는 7쌍 (26G~39G)
  | 'CENTER_PAIR' // 중앙 마주보는 4쌍 (18G~25G)
  | 'RIGHT_PAIR' // 우측 페어 좌석 (40G~47G)

/** 좌석 배치도에 그려질 좌석 1개 */
export interface SeatMapItem {
  id: string
  /** 화면 표시용 좌석 번호. 백엔드 Seat.seatNumber에 대응 */
  seatNumber: string
  /** 백엔드 Seat.seatType(GENERAL/FIXED)에 대응하는 상위 카테고리 */
  category: SeatCategory
  /** 배치도 렌더링용 화면 상태 */
  status: SeatDisplayStatus
  /** 시각적 그룹 (레이아웃 배치 힌트) */
  group: SeatLayoutGroup
  /** 배치도 컨테이너 기준 절대 좌표 (%) */
  x: number
  y: number
  /** 책상(반원) 아이콘이 붙는 방향 */
  deskDirection: 'up' | 'down' | 'left' | 'right'
}

/** 배치도 내 비-좌석 구역 (사물함, 탕비실 등) */
export interface SeatMapZone {
  id: string
  label: string
  x: number
  y: number
  width: number
  height: number
  /** 점선 테두리 여부 (사물함실) */
  dashed?: boolean
}

/** 하단 요약 바 항목 1개 */
export interface SeatSummaryItem {
  id: string
  label: string
  available: number
  total: number
  /** 강조 표시 여부 (자유석 노란 배경) */
  highlighted: boolean
}
