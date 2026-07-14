import type { SeatSummaryItem } from '../../types/seat'

interface SeatSummaryBarProps {
  items: SeatSummaryItem[]
}

/** 좌석/사물함 현황 3분할 요약 바 */
export default function SeatSummaryBar({ items }: SeatSummaryBarProps) {
  return (
    <div className="grid grid-cols-3 border-t border-gray-200">
      {items.map((item) => (
        <div
          key={item.id}
          className={`flex flex-col items-center gap-1 py-3 ${item.highlighted ? 'bg-yellow-100' : 'bg-white'}`}
        >
          <p className="text-base font-bold text-gray-900">
            {item.available}/{item.total}
          </p>
          <p className="text-xs text-gray-500">{item.label}</p>
        </div>
      ))}
    </div>
  )
}
