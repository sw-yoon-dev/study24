import MembershipHeader from '../components/membership/MembershipHeader'
import MembershipPager from '../components/membership/MembershipPager'
import { mockMemberInfo, mockMemberships } from '../mocks/homeMock'

export default function MyMembershipPage() {
  return (
    <div className="flex flex-col gap-5 pb-4">
      <MembershipHeader memberInfo={mockMemberInfo} />
      <MembershipPager tickets={mockMemberships} />
    </div>
  )
}
