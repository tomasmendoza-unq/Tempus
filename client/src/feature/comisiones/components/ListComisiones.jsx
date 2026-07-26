import { ActionList } from "../../../shared/components/actionList/ActionList"
import { ListCard } from "../../../shared/components/listCard/ListCard"

export const ListComisiones = ({ comisiones, onAprobar }) => (
	<ActionList
		items={comisiones}
		headerTitle="Cursadas actuales"
		emptyMessage="No hay registros de cursadas actuales."
		renderItem={(com) => (
			<ListCard
				key={com.comisionId}
				title={com.comisionNombre}
				subtitle={`Materia: ${com.materiaNombre}`}
			>
				<button onClick={() => onAprobar(com.comisionId)}>Aprobar</button>
			</ListCard>
		)}
	/>
)
