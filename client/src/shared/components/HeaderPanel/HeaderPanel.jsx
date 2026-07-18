import "./HeaderPanel.css"

export const HeaderPanel = ({
	title,
	children,
	className = "",
	contentClassName = "",
	variant = "solid",
}) => {
	return (
		<article className={`header-panel header-panel--${variant} ${className}`.trim()}>
			<header className="header-panel__header">
				<h1>{title}</h1>
			</header>
			<section className={`header-panel__content ${contentClassName}`.trim()}>
				{children}
			</section>
		</article>
	)
}
