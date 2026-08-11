export function Grid({ children, className = "" }) {
	return (
		<div
			className={`grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-3 ${className}`}
		>
			{children}
		</div>
	)
}
