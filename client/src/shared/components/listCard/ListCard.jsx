export const ListCard = ({ title, subtitle, children }) => {
	return (
		<div>
			<h3>{title}</h3>
			<p>{subtitle}</p>
			{children}
		</div>
	)
}
