import { BrowserRouter, Route, Routes } from 'react-router-dom'
import BottomTabBar from './components/layout/BottomTabBar'
import MobileFrame from './components/layout/MobileFrame'
import MyInfoPlaceholderPage from './pages/MyInfoPlaceholderPage'
import MyMembershipPage from './pages/MyMembershipPage'
import SeatPlaceholderPage from './pages/SeatPlaceholderPage'

function App() {
  return (
    <BrowserRouter>
      <MobileFrame>
        <div className="pb-20">
          <Routes>
            <Route path="/" element={<MyMembershipPage />} />
            <Route path="/seats" element={<SeatPlaceholderPage />} />
            <Route path="/my-info" element={<MyInfoPlaceholderPage />} />
          </Routes>
        </div>
        <BottomTabBar />
      </MobileFrame>
    </BrowserRouter>
  )
}

export default App
