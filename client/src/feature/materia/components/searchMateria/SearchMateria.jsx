import { useState } from "react"
import { HeaderPanel } from "../../../../shared/components/HeaderPanel/HeaderPanel"
import "./style/SearchMateria.css"

export const SearchMateria = ({ materias, onSelectMateria }) => {
	const [searchTerm, setSearchTerm] = useState("")

	const materiasAMostrar = searchTerm.trim()
		? materias.filter((m) =>
				m.materiaNombre.toLowerCase().includes(searchTerm.toLowerCase())
			)
		: materias

	const handleSelectMateria = (m) => {
		setSearchTerm("")
		onSelectMateria(m)
	}

	return (
		<HeaderPanel
			title="Materias disponibles"
			variant="plain"
			className="search-materia"
			contentClassName="search-materia__body"
		>
			<section className="search-materia-container">
				<form
					method="post"
					className="search-form"
					onSubmit={(e) => e.preventDefault()}
				>
					<input
						type="text"
						className="w-full"
						placeholder="Buscar materia..."
						value={searchTerm}
						onChange={(e) => setSearchTerm(e.target.value)}
					/>
				</form>
				<section className="search-results">
					{materiasAMostrar.map((m, idx) => (
						<button
							key={idx}
							onClick={() => handleSelectMateria(m)}
							className="w-full text-left px-6 py-4 text-sm font-medium border-b border-gray-50 transition-colors text-gray-600 hover:bg-gray-100"
						>
							{m.materiaNombre}
						</button>
					))}
				</section>
			</section>
		</HeaderPanel>
	)
}
