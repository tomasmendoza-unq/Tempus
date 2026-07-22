import { Link } from "react-router-dom"

export const NavLink = ({ to, onClick, children }) => {
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
