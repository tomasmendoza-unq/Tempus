import { useState } from "react"
import { Book, Plus, Trash2 } from "feather-icons-react"
import { CarreraModalContent } from "../../../../components/Carrera/CarreraModalContent"
import "./SuscripcionCarreras.css"
import Modal from "../../../../components/Ui/Modal/Modal"
import { UseGetCarrerasDisponibles } from "../../../carreras/hook/use-get-carreras-disponibles"
import { ActionList } from "../../../../shared/components/actionList/ActionList"

export function SuscripcionCarreras({
	carrerasUsuario,
	onDesuscribir,
	onSuscribir,
}) {
	const [isModalOpen, setIsModalOpen] = useState(false)
	const { carrerasDisponibles, loading, error, fetchCarrerasDisponibles } =
		UseGetCarrerasDisponibles()

	const handleAbrirModal = async () => {
		await fetchCarrerasDisponibles()
		setIsModalOpen(true)
	}

	const handleSeleccion = async (idCarrera) => {
		await onSuscribir?.(idCarrera)
		setIsModalOpen(false)
	}

	return (
		<section className="suscripcion-carreras">
			<ActionList
				items={carrerasUsuario}
				headerTitle="Mis Carreras"
				headerAction={
					<button
						onClick={handleAbrirModal}
						className="suscripcion-carreras__add-btn"
					>
						<Plus className="suscripcion-carreras__add-icon" />
						Nueva Carrera
					</button>
				}
				emptyMessage="Aún no estás suscripto a ninguna carrera."
				renderItem={(carrera) => (
					<div key={carrera.idCarrera} className="suscripcion-carreras__item">
						<div className="suscripcion-carreras__item-info">
							<span className="suscripcion-carreras__item-name">
								{carrera.nombreCarrera}
							</span>
							<span className="suscripcion-carreras__item-code">
								Código: {carrera.idCarrera}
							</span>
						</div>

						<button
							onClick={() => onDesuscribir?.(carrera.idCarrera)}
							className="suscripcion-carreras__delete-btn"
							title="Dar de baja carrera"
						>
							<Trash2 className="suscripcion-carreras__delete-icon" />
						</button>
					</div>
				)}
			/>

			<Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)}>
				<CarreraModalContent
					carrerasDisponibles={carrerasDisponibles}
					loading={loading}
					error={error}
					onSeleccionar={handleSeleccion}
				/>
			</Modal>
		</section>
	)
}
