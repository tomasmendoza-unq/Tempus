import { ModalHeader } from "../../shared/components/modal/ModalHeader"
import { HorarioGrid } from "./HorarioGrid"

export function HorarioModalContent({ horarioData }) {
	return (
		<div className="w-full max-w-6xl min-w-[900px] mt-4">
			<ModalHeader
				title="Cronograma de Clases"
				subtitle="Tempus - Ciclo Lectivo 2026"
			/>
			<HorarioGrid horario={horarioData} />
		</div>
	)
}
