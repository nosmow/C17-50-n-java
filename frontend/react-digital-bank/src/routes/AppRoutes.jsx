import { useContext } from 'react'
import { Route, Routes } from 'react-router-dom'
import { routes } from './routes'
import { Register } from '../pages/Register'
import { RegisterEnterprise } from '../pages/RegisterEnterprise'
import { Error404 } from '../pages/Error404'
import { Home } from '../pages/Home'
import { Login } from '../pages/Login'
import { Admin } from '../pages/Admin'
import { ProtectedRoute } from '../components/routes/ProtectedRoute'
import { UserContext } from '../context/User/UserContext'
import { Send } from '../pages/Send'
import { Transactions } from '../pages/Transactions'
import { Dashboard } from '../pages/Dashboard'
import { Maintain } from '../pages/Maintain'



export function AppRoutes() {

    const [user, setUser] = useContext(UserContext)

    return (
                <Routes>

                  <Route index element={<Login />} />
                  <Route path={routes.maintain} element={<Maintain />} />
                  <Route path={routes.login} element={<Login />} />
                  <Route path={routes.register} element={<Register />} />
                  <Route path={routes.enterpriseRegister} element={<RegisterEnterprise />} />

                  <Route element={<ProtectedRoute isAllowed={!!user.token} />}>
                      <Route path={routes.home} element={<Home />} />
                      <Route path={routes.send} element={<Send />} />
                      <Route path={routes.transactions} element={<Transactions />} />
                      <Route path={routes.dashboard} element={<Dashboard />} />
                  </Route>
                  
                  <Route element={<ProtectedRoute isAllowed={!!user.token && user.role=="ROLE_ADMIN"} />}>
                      <Route path={routes.admin} element={<Admin />} />
                  </Route>

                  <Route path={routes.error} element={<Error404 />} />
                </Routes>
    )
  }
  