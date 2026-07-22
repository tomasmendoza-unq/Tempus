import { useState } from "react"
import { HamburgerButton } from "./components/HamburgerButton"
import { Overlay } from "../overlay/Overlay"
import { MenuPanel } from "./components/menuPanel/MenuPanel"
import { MenuHeader } from "./components/menuHeader/MenuHeader"
import { MenuContent } from "./components/menuContent/MenuContent"
import { MenuActions } from "./components/menuActions/MenuActions"

export const HamburgerMenu = ({ navLinks, footer }) => {
	const [isOpen, setIsOpen] = useState(false)
	const closeMenu = () => setIsOpen(false)

	return (
		<>
			<HamburgerButton onClick={() => setIsOpen(true)} />
			<Overlay isOpen={isOpen} onClick={closeMenu} />

			<MenuPanel isOpen={isOpen}>
				<MenuHeader title="Menú" onClose={closeMenu} />
				<MenuContent navLinks={navLinks} onLinkClick={closeMenu} />
				<MenuActions footer={footer} />
			</MenuPanel>
		</>
	)
}
