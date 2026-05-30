import { useEffect } from "react"
import { useGetComisiones } from "../../hooks/use-get-comisiones"
import { ComisionCard } from "../../components/ComisionCard"
import { useGetDisponiblesMaterias } from "../../../materia/hook/use-get-disponibles-materias"
import "./styles/Comisiones.css"
import { SearchMateria } from "../../../materia/components/searchMateria/SearchMateria"

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
			<section className="search-materia">
				<h2>Materias disponibles</h2>
				<SearchMateria materias={materias} />
			</section>
			<section className="comisiones-container">
				{comisionesPorMateria.map((grupo) => (
					<article key={grupo.id} className="comisiones-panel">
						<header className="comisiones-panel__header">
							<h1>{grupo.nombre}</h1>
						</header>
						<section className="comisiones-panel__body">
							{grupo.comisiones.map((comision) => (
								<ComisionCard key={comision.comisionId} comision={comision} />
							))}
						</section>
					</article>
				))}
			</section>
		</section>
	)
}
