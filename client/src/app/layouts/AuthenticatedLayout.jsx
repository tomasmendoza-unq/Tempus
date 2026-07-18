import { Outlet } from "react-router"
import { ProtectedRoute } from "../../feature/auth/routes/ProtectedRoute.jsx"

export const AuthenticatedLayout = () => {
	return (
		<ProtectedRoute>
			<div className="authenticated-shell">
				<SideBar />
				<main className="authenticated-layout">
					<Outlet />
				</main>
			</div>
		</ProtectedRoute>
	)
}
