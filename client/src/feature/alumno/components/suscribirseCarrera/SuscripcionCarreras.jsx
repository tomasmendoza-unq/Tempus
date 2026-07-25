import { useState } from "react"
import { Book, Plus, Trash2 } from "feather-icons-react"
import { CarreraModalContent } from "../../../../components/Carrera/CarreraModalContent"
import { UseGetCarrerasDisponibles } from "../../hook/use-get-carreras-disponibles"
import "./SuscripcionCarreras.css"
import Modal from "../../../../components/Ui/Modal/Modal"

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
			<div className="suscripcion-carreras__header">
				<div className="suscripcion-carreras__title">
					<Book className="suscripcion-carreras__icon" />
					<h3>Mis Carreras</h3>
				</div>
				<button
					onClick={handleAbrirModal}
					className="suscripcion-carreras__add-btn"
				>
					<Plus className="suscripcion-carreras__add-icon" />
					Nueva Carrera
				</button>
			</div>

			<div className="suscripcion-carreras__list-container">
				{carrerasUsuario?.length > 0 ? (
					<div className="suscripcion-carreras__list">
						{carrerasUsuario.map((carrera) => (
							<div
								key={carrera.idCarrera}
								className="suscripcion-carreras__item"
							>
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
						))}
					</div>
				) : (
					<div className="suscripcion-carreras__empty">
						Aún no estás suscripto a ninguna carrera.
					</div>
				)}
			</div>

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
