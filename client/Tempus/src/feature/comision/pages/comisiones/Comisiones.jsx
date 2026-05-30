import { useEffect } from "react"
import { useGetComisiones } from "../../hooks/use-get-comisiones"
import { ComisionCard } from "../../components/ComisionCard"
import { useGetDisponiblesMaterias } from "../../../materia/hook/use-get-disponibles-materias"
import "./styles/Comisiones.css"
import { SearchMateria } from "../../../materia/components/searchMateria/SearchMateria"
import { HeaderPanel } from "../../../../shared/components/HeaderPanel/HeaderPanel"

export const Comisiones = () => {
	const { fetch, comisiones } = useGetComisiones()
	const { fetch: fetchMaterias, materias } = useGetDisponiblesMaterias()

	const comisionesPorMateria = comisiones.reduce((grupos, comision) => {
		const materiaNombre = comision.materia?.materiaNombre || "Comisiones"
		const materiaId = comision.materia?.materiaId || materiaNombre
		const grupoExistente = grupos.find((grupo) => grupo.id === materiaId)

		if (grupoExistente) {
			grupoExistente.comisiones.push(comision)
			return grupos
		}

		return [
			...grupos,
			{
				id: materiaId,
				nombre: materiaNombre,
				comisiones: [comision],
			},
		]
	}, [])

	useEffect(() => {
		fetch()
		fetchMaterias()
	}, [])

	return (
		<section className="comisiones-page">
			<HeaderPanel
				title="Materias disponibles"
				variant="plain"
				className="search-materia"
				contentClassName="search-materia__body"
			>
				<SearchMateria materias={materias} />
			</HeaderPanel>
			<section className="comisiones-container">
				{comisionesPorMateria.map((grupo) => (
					<HeaderPanel
						key={grupo.id}
						title={grupo.nombre}
						className="comisiones-panel"
						contentClassName="comisiones-panel__body"
					>
						{grupo.comisiones.map((comision) => (
							<ComisionCard key={comision.comisionId} comision={comision} />
						))}
					</HeaderPanel>
				))}
			</section>
		</section>
	)
}
