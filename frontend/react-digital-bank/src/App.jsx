// import './app.css'
import { BrowserRouter } from 'react-router-dom'
import { AppRoutes } from './routes/AppRoutes'
import './styles/mainStyles.css'
import { UserProvider } from './context/User/UserContext'

export function App() {
  return (<BrowserRouter>
            <UserProvider>
              <AppRoutes />
            </UserProvider>
          </BrowserRouter>
          )
}
