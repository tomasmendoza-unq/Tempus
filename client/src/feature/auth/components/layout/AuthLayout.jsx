import "./AuthLayout.css"

export default function AuthLayout({ title, subtitle, children, footer }) {
	return (
		<section className="auth-container">
			<div className="auth-content">
				<h1>{title}</h1>
				{subtitle && (
					<p className="text-center text-gray-500 text-sm -mt-2">{subtitle}</p>
				)}
				{children}
				{footer}
			</div>
		</section>
	)
}
