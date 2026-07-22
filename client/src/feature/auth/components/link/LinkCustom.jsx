import { Link } from "react-router-dom"
import "./LinkCustom.css"

export const LinkCustom = ({ to, text, textLink }) => {
	return (
		<p className="link-text">
			{text}{" "}
			<Link to={to} className="link">
				{textLink}
			</Link>
		</p>
	)
}
