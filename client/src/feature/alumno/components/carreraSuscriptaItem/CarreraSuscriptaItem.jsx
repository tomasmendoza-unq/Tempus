import { Trash2 } from "feather-icons-react"
import { CarreraItem } from "../../../carreras/components/carreraItem/CarreraItem"

export function CarreraSuscriptaItem({ carrera, onDesuscribir }) {
	return (
		<CarreraItem carrera={carrera}>
			<button
				onClick={() => onDesuscribir?.(carrera.idCarrera)}
				className="suscripcion-carreras__delete-btn"
				title="Dar de baja carrera"
			>
				<Trash2 className="suscripcion-carreras__delete-icon" />
			</button>
		</CarreraItem>
	)
}
