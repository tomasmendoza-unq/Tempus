import { Link } from "react-router-dom"
import { HamburgerMenu } from "../hamburgerMenu/HamburgerMenu"
import "./NavBar.css"

export const NavBar = ({ navLinks, footer }) => {
	return (
		<header className="navbar">
			<div className="navbar__brand">
				<Link to="/">
					<img
						src="https://res.cloudinary.com/dvkvlpq07/image/upload/v1785110781/navbar_uircg2.png"
						alt="Tempus"
						className="navbar__logo"
					/>
				</Link>
			</div>
			<HamburgerMenu navLinks={navLinks} footer={footer} />
		</header>
	)
}
