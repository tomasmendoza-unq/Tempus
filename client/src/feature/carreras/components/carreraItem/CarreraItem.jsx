import { BookOpen } from "feather-icons-react"

export function CarreraItem({ carrera, children }) {
	return (
		<div className="flex items-center justify-between p-4 bg-white rounded-xl border border-gray-100 hover:border-red-200 hover:shadow-md transition-all group">
			<div className="flex items-center gap-4">
				<div className="p-2 bg-red-50 rounded-lg text-red-950 group-hover:bg-red-950 group-hover:text-white transition-colors">
					<BookOpen className="w-5 h-5" />
				</div>
				<div className="flex flex-col">
					<span className="font-bold text-gray-800 group-hover:text-red-950 transition-colors">
						{carrera.nombreCarrera}
					</span>
					<span className="text-[10px] text-gray-400 uppercase font-semibold">
						UNQ - Código {carrera.idCarrera}
					</span>
				</div>
			</div>

			{children}
		</div>
	)
}
