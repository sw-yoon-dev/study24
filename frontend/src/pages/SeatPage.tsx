import SeatLegend from '../components/seat/SeatLegend'
import SeatMap from '../components/seat/SeatMap'
import SeatSummaryBar from '../components/seat/SeatSummaryBar'
import { mockSeatSummary, mockSeatZones, mockSeats } from '../mocks/seatMock'

export default function SeatPage() {
  return (
    <div className="flex flex-col gap-4 pb-4">
      <header className="flex items-center gap-2 px-4 pt-5">
        <span className="rounded bg-red-500 px-1.5 py-0.5 text-[10px] font-bold text-white">LIVE</span>
        <h1 className="text-lg font-bold text-gray-900">좌석</h1>
      </header>

      <SeatLegend />
      <SeatMap seats={mockSeats} zones={mockSeatZones} />
      <SeatSummaryBar items={mockSeatSummary} />
    </div>
  )
}
