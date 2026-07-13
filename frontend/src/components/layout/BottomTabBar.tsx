import { NavLink } from 'react-router-dom'

const TABS = [
  { to: '/', label: '나의 이용권', icon: '🎫', end: true },
  { to: '/seats', label: '좌석', icon: '🪑', end: false },
  { to: '/my-info', label: '나의 정보', icon: '👤', end: false },
]

export default function BottomTabBar() {
  return (
    <nav className="absolute bottom-0 w-full border-t border-gray-200 bg-white">
      <div className="flex">
        {TABS.map((tab) => (
          <NavLink
            key={tab.to}
            to={tab.to}
            end={tab.end}
            className={({ isActive }) =>
              `flex flex-1 flex-col items-center gap-1 py-2.5 text-xs font-medium ${
                isActive ? 'text-purple-600' : 'text-gray-400'
              }`
            }
          >
            <span className="text-xl leading-none">{tab.icon}</span>
            <span>{tab.label}</span>
          </NavLink>
        ))}
      </div>
    </nav>
  )
}
