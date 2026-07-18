import { useEffect, useState } from "react"
import { useGetComisiones } from "../../hooks/use-get-comisiones"
import { useGetDisponiblesMaterias } from "../../../materia/hook/use-get-disponibles-materias"
import { useComisionesPorMateria } from "../../hooks/use-comisiones-por-materia"
import { ComisionMateriaGroup } from "../../components/ComisionMateriaGroup"
import "./styles/Comisiones.css"
import { SearchMateria } from "../../../materia/components/searchMateria/SearchMateria"

export const Comisiones = () => {
	const [materiaSeleccionada, setMateriaSeleccionada] = useState(null)
	const { fetch, comisiones } = useGetComisiones()
	const { fetch: fetchMaterias, materias } = useGetDisponiblesMaterias()
	const comisionesPorMateria = useComisionesPorMateria(comisiones)

	useEffect(() => {
		fetch()
		fetchMaterias()
	}, [])

	const comisionesFiltrables = materiaSeleccionada
		? comisionesPorMateria.filter(
				(grupo) => grupo.id === materiaSeleccionada.materiaId
			)
		: comisionesPorMateria

	return (
		<section className="comisiones-page">
			<SearchMateria
				materias={materias}
				onSelectMateria={setMateriaSeleccionada}
			/>
			<section className="comisiones-container">
				{comisionesFiltrables.map((grupo) => (
					<ComisionMateriaGroup key={grupo.id} grupo={grupo} />
				))}
			</section>
		</section>
	)
}
