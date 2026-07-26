import { ActionList } from "../../../../shared/components/actionList/ActionList"
import { ListCard } from "../../../../shared/components/listCard/ListCard"

export const ListMaterias = ({ materias, onDesaprobar }) => (
	<ActionList
		items={materias}
		headerTitle="Materias aprobadas"
		emptyMessage="No hay registros de materias aprobadas."
		renderItem={(materia) => (
			<ListCard key={materia.materiaId} title={materia.materiaNombre}>
				<button onClick={() => onDesaprobar(materia.materiaId)}>
					Desaprobar
				</button>
			</ListCard>
		)}
	/>
)
