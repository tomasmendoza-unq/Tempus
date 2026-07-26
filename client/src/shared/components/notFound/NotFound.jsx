import { Link } from "react-router-dom"
import { Compass } from "feather-icons-react"
import "./NotFound.css"

export const NotFound = () => (
	<main className="not-found">
		<div className="not-found__card">
			<span className="not-found__eyebrow">Comisión no encontrada</span>

			<div className="not-found__code">
				<span className="not-found__digit">4</span>
				<span className="not-found__icon-wrap">
					<Compass className="not-found__icon" />
				</span>
				<span className="not-found__digit">4</span>
			</div>

			<h1 className="not-found__title">Esta página no está en el horario</h1>
			<p className="not-found__subtitle">
				Revisá la dirección o volvé a tu panel para seguir armando tu
				cuatrimestre.
			</p>

			<Link to="/" className="not-found__link">
				Volver al inicio
			</Link>
		</div>
	</main>
)
