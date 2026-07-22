import { Navigate } from "react-router"
import { useAuth } from "../hook/use-auth"

export const ProtectedRoute = ({ children }) => {
	const { user, loading } = useAuth()

	if (loading) return <Spinner />
	if (!user) return <Navigate to="/login" replace />

	return children
}
