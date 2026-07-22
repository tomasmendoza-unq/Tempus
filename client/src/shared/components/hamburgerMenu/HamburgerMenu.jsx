import { useState } from "react"
import { Link } from "react-router-dom"
import { Menu, X, User } from "feather-icons-react"

export const HamburgerMenu = ({ navLinks, footer }) => {
	const [isOpen, setIsOpen] = useState(false)

	const closeMenu = () => setIsOpen(false)

	return (
		<>
			<button
				onClick={() => setIsOpen(true)}
				className="text-white hover:text-red-400 transition-colors z-50"
				aria-label="Abrir menú"
			>
				<Menu size={28} />
			</button>

			<div
				className={`fixed inset-0 bg-black/60 z-40 transition-opacity duration-300 ${
					isOpen ? "opacity-100 visible" : "opacity-0 invisible"
				}`}
				onClick={closeMenu}
			/>

			<div
				className={`fixed top-0 right-0 h-full w-72 bg-red-950 z-50 shadow-2xl transform transition-transform duration-300 ease-in-out ${
					isOpen ? "translate-x-0" : "translate-x-full"
				}`}
			>
				<div className="flex items-center justify-between px-6 py-5 border-b border-red-900/50">
					<span className="text-white text-lg font-bold">Menú</span>
					<button
						onClick={closeMenu}
						className="text-red-300 hover:text-white transition-colors"
						aria-label="Cerrar menú"
					>
						<X size={24} />
					</button>
				</div>

				<nav className="flex flex-col px-4 py-4 gap-1">
					{navLinks.map((link) => (
						<NavLink key={link.path} to={link.path} onClick={closeMenu}>
							{link.name}
						</NavLink>
					))}
				</nav>

				<div className="absolute bottom-0 left-0 right-0 px-6 py-5 border-t border-red-900/50">
					{footer}
				</div>
			</div>
		</>
	)
}

function NavLink({ to, onClick, children }) {
	return (
		<Link
			to={to}
			onClick={onClick}
			className="text-red-100 hover:bg-red-900/60 hover:text-white px-4 py-3 rounded-lg transition-colors font-medium"
		>
			{children}
		</Link>
	)
}
