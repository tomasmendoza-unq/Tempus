import { MateriaGrid } from "../materiaGrid/MateriaGrid"
import { Spinner } from "../../../../shared/components/spinner/Spinner"

export function MateriaSelector({ materias, selectedIds, onToggle, cargando }) {
	if (cargando) return <Spinner />

	return (
		<section className="bg-white p-6 rounded-lg shadow border border-gray-200">
			<h2 className="text-2xl font-bold text-gray-700 text-center">
				Gestion Horarios
			</h2>
			<h3 className="text-lg font-semibold mb-4 text-gray-700">
				Selecciona tus materias
			</h3>
			<MateriaGrid
				materias={materias}
				selectedIds={selectedIds}
				onToggle={onToggle}
			/>
		</section>
	)
}
