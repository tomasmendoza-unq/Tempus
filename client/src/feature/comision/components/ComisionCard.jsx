import { HorarioList } from "../../horario/components/horarioList/HorarioList"
import "./style/ComisionCard.css"

export const ComisionCard = ({ comision }) => {
	return (
		<section className="comision-card">
			<header className="comision-card__header">
				<h2 className="comision-card__title">{comision.comisionNombre}</h2>
				<span className="comision-card__modality">
					{comision.modalidad || "Presencial"}
				</span>
			</header>
			<HorarioList horarios={comision.claseHorario || []} />
		</section>
	)
}
