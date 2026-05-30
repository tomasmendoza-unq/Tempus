import { HeaderPanel } from "../../../../shared/components/HeaderPanel/HeaderPanel"
import "./style/SearchMateria.css"

export const SearchMateria = ({ materias, onSelectMateria }) => {
	return (
		<HeaderPanel
			title="Materias disponibles"
			variant="plain"
			className="search-materia"
			contentClassName="search-materia__body"
		>
			<section className="search-materia-container">
				<input type="text" placeholder="Buscar materia..." />
				<section className="search-results">
					{materias.map((m, idx) => (
						<button
							key={idx}
							onClick={() => onSelectMateria(m)}
							className="w-full text-left px-6 py-4 text-sm font-medium border-b border-gray-50 transition-colors text-gray-600 hover:bg-gray-50"
						>
							{m.materiaNombre}
						</button>
					))}
				</section>
			</section>
		</HeaderPanel>
	)
}
