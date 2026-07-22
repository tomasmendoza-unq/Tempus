import { X } from "feather-icons-react"
import "./MenuHeader.css"

export const MenuHeader = ({ title = "Menú", onClose }) => (
	<div className="menu-header">
		<span className="menu-header__title">{title}</span>
		<button
			onClick={onClose}
			className="menu-header__close"
			aria-label="Cerrar menú"
		>
			<X size={24} />
		</button>
	</div>
)
