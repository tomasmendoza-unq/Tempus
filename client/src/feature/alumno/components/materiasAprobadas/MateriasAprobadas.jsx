import { ActionList } from "../../../../shared/components/actionList/ActionList"
import { MateriaAprobada } from "./MateriaAprobada"

export const MateriasAprobadas = ({ materias, onDesaprobar }) => (
	<ActionList
		items={materias}
		headerTitle="Materias aprobadas"
		emptyMessage="No hay registros de materias aprobadas."
		renderItem={(materia) => (
			<MateriaAprobada materia={materia} onDesaprobar={onDesaprobar} />
		)}
	/>
)
