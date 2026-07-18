export const HorarioList = ({ horarios = [] }) => {
	if (!horarios.length) {
		return <p className="horario-list__empty">Sin horarios</p>
	}

	return (
		<section className="horario-list">
			{horarios.map((h, i) => (
				<section key={i} className="horario-list__item">
					<span className="horario-list__day">{h.dia}</span>
					<span className="horario-list__time">
						{h.inicio?.substring(0, 5)} - {h.fin?.substring(0, 5)}
					</span>
				</section>
			))}
		</section>
	)
}
