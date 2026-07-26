import { Calendar } from "feather-icons-react"

export function Header({ title, children }) {
	return (
		<div className="flex items-center justify-between px-1">
			<h3 className="text-xl font-bold text-red-950 flex items-center gap-2 italic">
				<span className="w-1.5 h-6 bg-red-900 rounded-full"></span>
				{title}
			</h3>
			{children}
		</div>
	)
}
