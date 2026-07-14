import type { SeatDisplayStatus, SeatMapItem } from '../../types/seat'

interface SeatItemProps {
  seat: SeatMapItem
}

/** 좌석 상태별 박스 스타일 */
const STATUS_STYLE: Record<SeatDisplayStatus, string> = {
  AVAILABLE: 'border-gray-300 bg-white text-gray-700',
  UNAVAILABLE: 'border-gray-200 bg-gray-100 text-gray-400',
  LEAVING_SOON: 'border-gray-300 bg-white text-gray-700',
  FIXED_OCCUPIED: 'border-purple-300 bg-purple-50 text-purple-700',
}

/** 책상(반원) 아이콘 위치를 방향에 따라 지정 */
const DESK_POSITION: Record<SeatMapItem['deskDirection'], string> = {
  up: 'left-1/2 top-0 h-1.5 w-3 -translate-x-1/2 -translate-y-1/2 rounded-t-full',
  down: 'left-1/2 bottom-0 h-1.5 w-3 -translate-x-1/2 translate-y-1/2 rounded-b-full',
  left: 'left-0 top-1/2 h-3 w-1.5 -translate-x-1/2 -translate-y-1/2 rounded-l-full',
  right: 'right-0 top-1/2 h-3 w-1.5 translate-x-1/2 -translate-y-1/2 rounded-r-full',
}

/** 배치도 위 좌석 1개. 절대 좌표(x, y %)로 위치를 잡는다 */
export default function SeatItem({ seat }: SeatItemProps) {
  return (
    <div
      className="absolute -translate-x-1/2 -translate-y-1/2"
      style={{ left: `${seat.x}%`, top: `${seat.y}%` }}
    >
      <div
        className={`relative flex h-8 w-8 items-center justify-center rounded-md border text-[9px] font-semibold ${STATUS_STYLE[seat.status]}`}
      >
        <span className={`absolute border border-gray-300 bg-gray-100 ${DESK_POSITION[seat.deskDirection]}`} />
        {seat.status === 'LEAVING_SOON' && <span className="absolute -top-1 -right-1 text-[9px]">✅</span>}
        {seat.status === 'FIXED_OCCUPIED' && <span className="absolute -top-1 -right-1 text-[9px]">⭐</span>}
        {seat.seatNumber}
      </div>
    </div>
  )
}
