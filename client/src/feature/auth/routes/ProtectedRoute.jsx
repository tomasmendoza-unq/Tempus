import { Navigate } from "react-router"
import { useAuth } from "../hook/use-auth"

export const ProtectedRoute = ({ children }) => {
	const { user, loading } = useAuth()

	console.log("ProtectedRoute user:", user)
	if (loading) return <Spinner />
	if (!user) return <Navigate to="/login" replace />

	return children
}
