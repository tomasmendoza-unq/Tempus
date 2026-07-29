import { Grid } from "../../../../shared/components/grid/Grid"
import { MateriaButton } from "../materiaButton/MateriaButton"

export function MateriaGrid({ materias, selectedIds, onToggle }) {
	return (
		<Grid>
			{materias?.map((materia) => (
				<MateriaButton
					key={materia.materiaId}
					materia={materia}
					selected={selectedIds.includes(materia.materiaId)}
					onToggle={onToggle}
				/>
			))}
		</Grid>
	)
}
