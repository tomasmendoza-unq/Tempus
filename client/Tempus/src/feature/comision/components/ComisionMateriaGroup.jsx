import { HeaderPanel } from "../../../shared/components/HeaderPanel/HeaderPanel"
import { ComisionCard } from "./ComisionCard"

export const ComisionMateriaGroup = ({ grupo }) => {
	return (
		<HeaderPanel
			key={grupo.id}
			title={grupo.nombre}
			className="comisiones-panel"
			contentClassName="comisiones-panel__body"
		>
			{grupo.comisiones.map((comision) => (
				<ComisionCard key={comision.comisionId} comision={comision} />
			))}
		</HeaderPanel>
	)
}
