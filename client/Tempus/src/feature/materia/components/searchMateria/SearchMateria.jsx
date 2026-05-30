import "./style/SearchMateria.css"

export const SearchMateria = ({ materias }) => {
	return (
		<section className="search-materia-container">
			<input type="text" placeholder="Buscar materia..." />
			<section className="search-results">
				{materias.map((m, idx) => (
					<button
						key={idx}
						className="w-full text-left px-6 py-4 text-sm font-medium border-b border-gray-50 transition-colors text-gray-600 hover:bg-gray-50"
					>
						{m.materiaNombre}
					</button>
				))}
			</section>
		</section>
	)
}
