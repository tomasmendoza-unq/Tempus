import { Menu } from "feather-icons-react"

export const HamburgerButton = ({ onClick }) => {
	return (
		<button
			onClick={() => onClick()}
			className="text-white hover:text-red-400 transition-colors z-50"
			aria-label="Abrir menú"
		>
			<Menu size={28} />
		</button>
	)
}
