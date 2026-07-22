export const Overlay = ({ isOpen, onClick }) => (
	<div
		className={`fixed inset-0 bg-black/60 z-40 transition-opacity duration-300 ${
			isOpen ? "opacity-100 visible" : "opacity-0 invisible"
		}`}
		onClick={onClick}
	/>
)
