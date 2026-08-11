import { SelectableButton } from "../../../../shared/components/selectedButton/SelectableButton"

export function MateriaButton({ materia, selected, onToggle }) {
	return (
		<SelectableButton
			label={materia.materiaNombre}
			selected={selected}
			onClick={() => onToggle(materia.materiaId)}
		/>
	)
}
