import { Trash2 } from "feather-icons-react"

export const MateriaAprobada = ({ materia, onDesaprobar }) => (
	<div
		key={materia.materiaId}
		className="p-4 flex justify-between items-center group"
	>
		<p className="text-sm font-medium text-gray-700">{materia.materiaNombre}</p>
		<button
			onClick={() => onDesaprobar(materia.materiaId)}
			className="p-1.5 text-red-400 hover:text-red-600 hover:bg-red-50 rounded-full transition-colors"
			title="Quitar de aprobadas"
		>
			<Trash2 size={16} />
		</button>
	</div>
)
