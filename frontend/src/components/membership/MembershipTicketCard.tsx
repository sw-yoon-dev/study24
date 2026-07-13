import type { MembershipTicket } from '../../types/membership'
import { formatCountdown, formatDateTime } from '../../utils/date'

interface MembershipTicketCardProps {
  ticket: MembershipTicket
}

const SEAT_CATEGORY_LABEL: Record<MembershipTicket['seatCategory'], string> = {
  FREE: '자유석',
  FIXED: '고정석',
}

export default function MembershipTicketCard({ ticket }: MembershipTicketCardProps) {
  return (
    <div className="rounded-2xl border border-gray-100 bg-white p-5 shadow-md">
      <div className="flex items-center gap-2">
        <span className="rounded-full bg-gray-600 px-3 py-1 text-xs font-semibold text-white">
          {ticket.isCheckedIn ? '입실' : '퇴실'}
        </span>
        <span className="text-sm font-medium text-gray-700">
          {SEAT_CATEGORY_LABEL[ticket.seatCategory]}
        </span>
      </div>

      <p className="mt-3 text-lg font-bold text-gray-900">{ticket.ticketLabel}</p>
      <p className="mt-1 text-xs text-gray-400">
        {formatDateTime(ticket.validFrom)} ~ {formatDateTime(ticket.validTo)}
      </p>

      <div className="mt-5 flex divide-x divide-gray-100">
        <div className="flex-1 text-center">
          <p className="text-xs text-gray-500">남은 이용시간</p>
          <p className="mt-1 text-2xl font-bold text-orange-500">
            {formatCountdown(ticket.remainingSeconds)}
          </p>
        </div>
        <div className="flex-1 text-center">
          <p className="text-xs text-gray-500">일일 무료 외출</p>
          <p className="mt-1 text-2xl font-bold text-gray-400">
            {formatCountdown(ticket.dailyFreeGoOutSeconds)}
          </p>
        </div>
      </div>

      <div className="mt-5 flex flex-col gap-2">
        <button
          type="button"
          className="rounded-full border border-gray-300 py-3 text-sm font-semibold text-gray-800"
        >
          시간 연장
        </button>
        <button
          type="button"
          className="rounded-full border border-gray-300 py-3 text-sm font-semibold text-gray-800"
        >
          사전 입실
        </button>
      </div>
    </div>
  )
}
