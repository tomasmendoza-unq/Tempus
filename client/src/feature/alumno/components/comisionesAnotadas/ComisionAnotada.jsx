import { CheckCircle } from "feather-icons-react"
import "./CursadaItem.css"

export const ComisionAnotada = ({ comision, onAprobar }) => {
	;<div className="cursada-item">
		<div className="cursada-item__info">
			<div className="cursada-item__header">
				<p className="cursada-item__materia">
					{comision.materia?.materiaNombre}
				</p>
				<span className="cursada-item__id-badge">{comision.comisionId}</span>
			</div>

			<div className="cursada-item__horarios">
				{comision.claseHorario?.map((clase, idx) => (
					<span key={idx} className="cursada-item__horario">
						{clase.dia}: {clase.inicio.slice(0, 5)} - {clase.fin.slice(0, 5)}hs
					</span>
				))}
			</div>
		</div>

		<div className="cursada-item__actions">
			<div className="cursada-item__aula-badge">
				AULA: {comision.aula || "S/A"}
			</div>

			<button
				onClick={() => onAprobar(comision.comisionId)}
				className="cursada-item__approve-btn"
				title="Marcar como aprobada"
			>
				<CheckCircle size={22} />
			</button>
		</div>
	</div>
}
