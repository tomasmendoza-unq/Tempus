import { Trash2 } from "feather-icons-react"

export const CarreraSuscriptaItem = ({ carrera, onDesuscribir }) => (
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
)
