/** 범례 4개 항목 */
const LEGEND_ITEMS = [
  { id: 'unavailable', label: '선택불가', icon: <span className="h-3 w-3 rounded-full bg-gray-300" /> },
  {
    id: 'available',
    label: '선택가능',
    icon: <span className="h-3 w-3 rounded-full border border-gray-400 bg-white" />,
  },
  { id: 'leaving-soon', label: '퇴실예정석', icon: <span className="text-xs">✅</span> },
  { id: 'fixed', label: '고정석', icon: <span className="text-xs">⭐</span> },
]

/** 좌석 배치도 상단 범례 (2x2) */
export default function SeatLegend() {
  return (
    <div className="grid grid-cols-2 gap-x-6 gap-y-2 px-4">
      {LEGEND_ITEMS.map((item) => (
        <div key={item.id} className="flex items-center gap-2 text-xs text-gray-600">
          {item.icon}
          <span>{item.label}</span>
        </div>
      ))}
    </div>
  )
}
