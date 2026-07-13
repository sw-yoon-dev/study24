import type { MemberInfo } from '../../types/membership'

interface MembershipHeaderProps {
  memberInfo: MemberInfo
}

export default function MembershipHeader({ memberInfo }: MembershipHeaderProps) {
  return (
    <div className="flex items-start justify-between px-4 pt-5">
      <div>
        <h1 className="text-xl font-bold text-gray-900">
          {memberInfo.displayName}의 이용권
        </h1>
        <div className="mt-2 flex items-center gap-2">
          <span className="rounded-full border border-purple-400 px-2.5 py-0.5 text-xs font-semibold text-purple-500">
            {memberInfo.operatingStatusLabel}
          </span>
          <span className="text-sm text-gray-500">
            {memberInfo.operatingHoursLabel}
          </span>
        </div>
      </div>

      <button
        type="button"
        className="flex flex-col items-center gap-1 text-gray-500"
      >
        <span className="text-xl leading-none">🎫➕</span>
        <span className="text-xs">이용권 구매</span>
      </button>
    </div>
  )
}
