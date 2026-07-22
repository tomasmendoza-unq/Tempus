import { Link } from "react-router-dom"
import { HamburgerMenu } from "../hamburgerMenu/HamburgerMenu"
import "./NavBar.css"

export default function NavBar({ navLinks, footer }) {
	return (
		<header className="navbar">
			<div className="navbar__brand">
				<Link to="/">
					<h1 className="navbar__title">Tempus</h1>
				</Link>
			</div>
			<HamburgerMenu navLinks={navLinks} footer={footer} />
		</header>
	)
}
