import { useState } from "react"
import { ActionList } from "../../../../shared/components/actionList/ActionList"
import { Calendar } from "feather-icons-react"
import Modal from "../../../../components/Ui/Modal/Modal"
import { HorarioModalContent } from "../../../../components/Horario/HorarioModalContent"
import { ComisionAnotada } from "./ComisionAnotada"

export const ComisionesAnotadas = ({ comisiones, onAprobar }) => {
	const [isModalOpen, setIsModalOpen] = useState(false)

	return (
		<>
			<ActionList
				items={comisiones}
				headerTitle="Cursadas actuales"
				headerAction={
					<button
						onClick={() => setIsModalOpen(true)}
						className="flex items-center gap-2 text-[10px] font-black uppercase tracking-widest bg-red-950 text-white px-4 py-2 rounded-lg hover:bg-red-900 transition-all shadow-md active:scale-95"
					>
						<Calendar size={14} />
						Ver Horario Completo
					</button>
				}
				emptyMessage="No hay registros de cursadas actuales."
				renderItem={(com) => (
					<ComisionAnotada comision={com} onAprobar={onAprobar} />
				)}
			/>
			<Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)}>
				<HorarioModalContent horarioData={comisiones} />
			</Modal>
		</>
	)
}
