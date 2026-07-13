import { useState } from 'react'
import type { MembershipTicket } from '../../types/membership'
import MembershipTicketCard from './MembershipTicketCard'

interface MembershipPagerProps {
  tickets: MembershipTicket[]
}

export default function MembershipPager({ tickets }: MembershipPagerProps) {
  const [index, setIndex] = useState(0)

  if (tickets.length === 0) {
    return (
      <p className="px-4 py-10 text-center text-sm text-gray-400">
        보유한 이용권이 없습니다.
      </p>
    )
  }

  const isFirst = index === 0
  const isLast = index === tickets.length - 1

  return (
    <div className="px-4">
      <MembershipTicketCard ticket={tickets[index]} />

      <div className="mt-4 flex items-center justify-center gap-6">
        <button
          type="button"
          aria-label="이전 이용권"
          disabled={isFirst}
          onClick={() => setIndex((i) => Math.max(0, i - 1))}
          className="text-xl text-gray-400 disabled:opacity-30"
        >
          ←
        </button>
        <span className="text-sm text-gray-500">
          {index + 1}/{tickets.length}
        </span>
        <button
          type="button"
          aria-label="다음 이용권"
          disabled={isLast}
          onClick={() => setIndex((i) => Math.min(tickets.length - 1, i + 1))}
          className="text-xl text-gray-400 disabled:opacity-30"
        >
          →
        </button>
      </div>
    </div>
  )
}
