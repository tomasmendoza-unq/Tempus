import { Outlet } from "react-router"
import { ProtectedRoute } from "../../feature/auth/routes/ProtectedRoute.jsx"
import NavBar from "../../shared/components/navbar/Navbar.jsx"

export const AuthenticatedLayout = () => {
	return (
		<ProtectedRoute>
			<div className="authenticated-shell">
				<NavBar />
				<main className="authenticated-layout">
					<Outlet />
				</main>
			</div>
		</ProtectedRoute>
	)
}
