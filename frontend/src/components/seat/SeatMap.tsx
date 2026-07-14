import type { SeatMapItem, SeatMapZone } from '../../types/seat'
import SeatItem from './SeatItem'

interface SeatMapProps {
  seats: SeatMapItem[]
  zones: SeatMapZone[]
}

/** 좌석 배치도 본문. 좌표(%) 기반 절대 위치로 좌석과 구역을 렌더링한다 */
export default function SeatMap({ seats, zones }: SeatMapProps) {
  return (
    <div className="relative mx-4 aspect-[4/5] rounded-xl border border-gray-200 bg-gray-50">
      {/* 스터디룸 페어(32G~39G) 열 사이 칸막이 */}
      <span className="absolute h-[47%] w-px bg-gray-300" style={{ left: '32.5%', top: '14%' }} />

      {/* 중앙 페어 열 사이 구분선 */}
      <span className="absolute h-[38%] w-px bg-gray-300" style={{ left: '60.5%', top: '16%' }} />

      {/* 우측 페어(40G~47G) 열 사이 칸막이 */}
      <span className="absolute h-[35%] w-px bg-gray-300" style={{ left: '89%', top: '21%' }} />

      {/* 비-좌석 구역 (사물함, 탕비실, 사물함실) */}
      {zones.map((zone) => (
        <div
          key={zone.id}
          className={`absolute flex items-start justify-center rounded-md border text-[10px] text-gray-400 ${
            zone.dashed ? 'border-dashed border-gray-300' : 'border-gray-200 bg-white'
          }`}
          style={{
            left: `${zone.x}%`,
            top: `${zone.y}%`,
            width: `${zone.width}%`,
            height: `${zone.height}%`,
          }}
        >
          <span className="mt-1">{zone.label}</span>
        </div>
      ))}

      {/* 탕비실 → 사물함실 통로 문 (두 구역 사이 틈을 잇는 통로 표시) */}
      <span className="absolute border-y border-dashed border-gray-300" style={{ left: '54%', top: '87%', width: '4%', height: '5%' }} />

      {/* 사물함실 → 스터디룸 통로 문 (사물함실 상단과 좌석 구역 사이 틈을 잇는 통로 표시) */}
      <span className="absolute border-x border-dashed border-gray-300" style={{ left: '70%', top: '68%', width: '8%', height: '4%' }} />

      {/* 좌석 */}
      {seats.map((seat) => (
        <SeatItem key={seat.id} seat={seat} />
      ))}

      {/* 입구 표시 */}
      <div className="absolute flex flex-col items-center gap-1" style={{ left: '7%', top: '97%' }}>
        <span className="text-[10px] text-gray-400">입구</span>
        <span className="h-2 w-2 rounded-full bg-orange-500" />
      </div>
    </div>
  )
}
