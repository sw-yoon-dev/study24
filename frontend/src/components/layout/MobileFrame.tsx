import type { ReactNode } from 'react'

interface MobileFrameProps {
  children: ReactNode
}

/** 뷰포트가 모바일보다 넓을 때도 폭을 고정해 앱처럼 보이게 하는 레이아웃 */
export default function MobileFrame({ children }: MobileFrameProps) {
  return (
    <div className="flex min-h-screen justify-center bg-gray-200">
      <div className="relative min-h-screen w-full max-w-[520px] bg-gray-50 shadow-xl">
        {children}
      </div>
    </div>
  )
}
