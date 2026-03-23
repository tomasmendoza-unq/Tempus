import { Navigate } from "react-router-dom"
import { useAuth } from "../hooks/useAuth"
import { ROUTES } from "../../constants"
import { getJwtPayload } from "../helpers/jwt"

export const ProtectedRoute = ({ children, allowedRoles }) => {
  const { isAuthenticated, cargando } = useAuth()

  const data = isAuthenticated ? getJwtPayload() : null

  if (cargando) {
    return (
      <div className="flex justify-center items-center h-64">
        <p className="text-xl font-bold text-red-950">Cargando sesión...</p>
      </div>
    )
  }

  if (!isAuthenticated) return <Navigate to={ROUTES.LOGIN} />

  if (allowedRoles && allowedRoles.length > 0) {
    if (!data || !allowedRoles.includes(data.role)) {
      return <Navigate to={ROUTES.HOME || "/"} replace />
    }
  }

  return children
}
