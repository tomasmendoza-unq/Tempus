import { Link } from "react-router-dom"
import { useAuth } from "../../../feature/auth/hook/use-auth"
import { HamburgerMenu } from "../hamburgerMenu/HamburgerMenu"

export default function NavBar({ navLinks, footer }) {
	const { isAuthenticated, usuario, logout } = useAuth()

	return (
		<header className="flex items-center justify-between px-8 py-4 shadow bg-red-950 text-white">
			<div className="flex items-center gap-8">
				<Link to="/" className="...">
					<h1 className="text-2xl font-bold">Tempus</h1>
				</Link>
				{/* {isAuthenticated && <CarreraSelector />} */}
			</div>
			<HamburgerMenu
				isAuthenticated={isAuthenticated}
				usuario={usuario}
				logout={logout}
				navLinks={navLinks}
				footer={footer}
			/>
		</header>
	)
}
