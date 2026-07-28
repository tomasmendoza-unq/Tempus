export function MateriaButton({ materia, selected, onToggle }) {
	return (
		<button
			onClick={() => onToggle(materia.materiaId)}
			className={`p-3 text-left rounded-lg border transition-all ${
				selected
					? "bg-red-50 border-red-950 text-red-950 font-bold ring-1 ring-red-950"
					: "bg-gray-50 border-gray-200 text-gray-600 hover:bg-gray-100"
			}`}
		>
			{materia.materiaNombre}
		</button>
	)
}
