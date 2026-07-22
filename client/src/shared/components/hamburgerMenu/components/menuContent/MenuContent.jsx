import { NavLink } from "../../../navLink/NavLink"
import "./MenuContent.css"

export const MenuContent = ({ navLinks, closeMenu }) => {
	return (
		<nav>
			{navLinks.map((link) => (
				<NavLink key={link.path} to={link.path} onClick={closeMenu}>
					{link.name}
				</NavLink>
			))}
		</nav>
	)
}
