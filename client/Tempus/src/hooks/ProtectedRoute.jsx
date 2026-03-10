import { Navigate } from "react-router-dom"
import { useAuth } from "../hooks/useAuth"
import { ROUTES } from "../../constants"

export const ProtectedRoute = ({ children }) => {
  const { isAuthenticated, cargando } = useAuth()

  if (cargando) {
    return (
      <div className="flex justify-center items-center h-64">
        <p className="text-xl font-bold text-red-950">Cargando sesión...</p>
      </div>
    )
  }

  return isAuthenticated ? children : <Navigate to={ROUTES.LOGIN} />
}