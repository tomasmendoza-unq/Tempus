import { MateriaButton } from "../materiaButton/MateriaButton"

export function MateriaGrid({ materias, selectedIds, onToggle }) {
	return (
		<div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-3">
			{materias?.map((materia) => (
				<MateriaButton
					key={materia.materiaId}
					materia={materia}
					selected={selectedIds.includes(materia.materiaId)}
					onToggle={onToggle}
				/>
			))}
		</div>
	)
}
