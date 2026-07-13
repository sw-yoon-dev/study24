import type { MemberInfo, MembershipTicket } from '../types/membership'

export const mockMemberInfo: MemberInfo = {
  displayName: '7324님',
  operatingStatusLabel: '운영중',
  operatingHoursLabel: '24시간',
}

export const mockMemberships: MembershipTicket[] = [
  {
    id: 'ticket-1',
    isCheckedIn: false,
    seatCategory: 'FREE',
    ticketLabel: '시간권 1550시간',
    validFrom: '2022-10-05T15:21:00',
    validTo: '2032-04-28T23:59:00',
    remainingSeconds: 146 * 3600 + 27 * 60 + 14,
    dailyFreeGoOutSeconds: 50 * 60,
  },
  {
    id: 'ticket-2',
    isCheckedIn: true,
    seatCategory: 'FIXED',
    ticketLabel: '1개월 정기권',
    validFrom: '2026-07-01T00:00:00',
    validTo: '2026-07-31T23:59:00',
    remainingSeconds: 18 * 24 * 3600,
    dailyFreeGoOutSeconds: 30 * 60,
  },
]
