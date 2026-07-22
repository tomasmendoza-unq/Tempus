import "./MenuPanel.css"

export const MenuPanel = ({ isOpen, children }) => (
	<div className={`menu-panel ${isOpen ? "menu-panel--open" : ""}`}>
		{children}
	</div>
)
