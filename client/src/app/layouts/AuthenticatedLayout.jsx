import { Outlet } from "react-router"
import { ProtectedRoute } from "../../feature/auth/routes/ProtectedRoute.jsx"
import NavBar from "../../shared/components/navbar/Navbar.jsx"
import { NavLinks } from "./NavLinks.js"
import { UserFooter } from "./components/UserFooter"

export const AuthenticatedLayout = () => {
	return (
		<ProtectedRoute>
			<div className="authenticated-shell">
				<NavBar navLinks={NavLinks} footer={<UserFooter />} />
				<main className="authenticated-layout">
					<Outlet />
				</main>
			</div>
		</ProtectedRoute>
	)
}
