import type { SeatMapItem, SeatMapZone, SeatSummaryItem } from '../types/seat'

/** 좌측 세로 1열: 1G~8G. 이미지 기준 상단 9G 라인부터 시작해 아래로 나열 */
const leftColumn: SeatMapItem[] = Array.from({ length: 8 }, (_, i) => {
  const num = i + 1
  return {
    id: `seat-${num}`,
    seatNumber: `${num}G`,
    category: 'FREE',
    status: 'AVAILABLE',
    group: 'LEFT_COLUMN',
    x: 7,
    y: 62 - i * 7.5,
    deskDirection: 'right',
  } as SeatMapItem
})

/** 최상단 가로 1열: 9G~17G. 17G는 고정석(별 아이콘) */
const topRow: SeatMapItem[] = Array.from({ length: 9 }, (_, i) => {
  const num = 9 + i
  const isFixed = num === 17
  return {
    id: `seat-${num}`,
    seatNumber: `${num}G`,
    category: isFixed ? 'FIXED' : 'FREE',
    status: isFixed ? 'FIXED_OCCUPIED' : 'AVAILABLE',
    group: 'TOP_ROW',
    x: 20 + i * 8.5,
    y: 5,
    deskDirection: 'down',
  } as SeatMapItem
})

/** 스터디룸 옆 마주보는 7쌍: 좌측열(33~39) · 우측열(32~26) */
const studyRoomPairs: SeatMapItem[] = [
  [33, 32],
  [34, 31],
  [35, 30],
  [36, 29],
  [37, 28],
  [38, 27],
  [39, 26],
].flatMap(([left, right], rowIdx) => {
  const y = 17 + rowIdx * 7.3
  return [
    {
      id: `seat-${left}`,
      seatNumber: `${left}G`,
      category: 'FREE',
      status: 'AVAILABLE',
      group: 'STUDY_ROOM_PAIR',
      x: 27,
      y,
      deskDirection: 'right',
    },
    {
      id: `seat-${right}`,
      seatNumber: `${right}G`,
      category: 'FREE',
      status: 'AVAILABLE',
      group: 'STUDY_ROOM_PAIR',
      x: 38,
      y,
      deskDirection: 'left',
    },
  ] as SeatMapItem[]
})

/** 중앙 마주보는 4쌍: 22G-21G, 23G-20G, 24G-19G, 25G-18G (열 사이 세로 구분선) */
const centerPairs: SeatMapItem[] = [
  [22, 21],
  [23, 20],
  [24, 19],
  [25, 18],
].flatMap(([left, right], rowIdx) => {
  const y = 19 + rowIdx * 7.3
  return [
    {
      id: `seat-${left}`,
      seatNumber: `${left}G`,
      category: 'FREE',
      status: 'AVAILABLE',
      group: 'CENTER_PAIR',
      x: 55,
      y,
      deskDirection: 'right',
    },
    {
      id: `seat-${right}`,
      seatNumber: `${right}G`,
      category: 'FREE',
      status: 'AVAILABLE',
      group: 'CENTER_PAIR',
      x: 66,
      y,
      deskDirection: 'left',
    },
  ] as SeatMapItem[]
})

/** 우측 페어 좌석: 46G/47G, 44G/45G, 42G/43G는 선택가능, 40G/41G는 선택불가(회색조) */
const rightPairs: SeatMapItem[] = [
  { left: 46, right: 47, y: 24, status: 'AVAILABLE' as const },
  { left: 44, right: 45, y: 33, status: 'AVAILABLE' as const },
  { left: 42, right: 43, y: 42, status: 'AVAILABLE' as const },
  { left: 40, right: 41, y: 52, status: 'UNAVAILABLE' as const },
].flatMap(({ left, right, y, status }) => [
  {
    id: `seat-${left}`,
    seatNumber: `${left}G`,
    category: 'FREE',
    status,
    group: 'RIGHT_PAIR',
    x: 85,
    y,
    deskDirection: 'up',
  },
  {
    id: `seat-${right}`,
    seatNumber: `${right}G`,
    category: 'FREE',
    status,
    group: 'RIGHT_PAIR',
    x: 93,
    y,
    deskDirection: 'up',
  },
] as SeatMapItem[])

export const mockSeats: SeatMapItem[] = [
  ...leftColumn,
  ...topRow,
  ...studyRoomPairs,
  ...centerPairs,
  ...rightPairs,
]

export const mockSeatZones: SeatMapZone[] = [
  { id: 'zone-pantry', label: '탕비실', x: 4, y: 72, width: 50, height: 23 },
  { id: 'zone-locker-room', label: '사물함실', x: 58, y: 72, width: 36, height: 23 },
]

export const mockSeatSummary: SeatSummaryItem[] = [
  { id: 'free', label: '자유석', available: 45, total: 46, highlighted: true },
  { id: 'fixed', label: '고정석', available: 1, total: 1, highlighted: false },
  { id: 'locker', label: '사물함', available: 7, total: 75, highlighted: false },
]
