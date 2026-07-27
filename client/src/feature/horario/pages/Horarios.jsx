import { useEffect, useState } from "react"
import { useGenerarHorario } from "../../../hooks/useHorario"
import { MateriaSelector } from "../../../components/Horario/MateriaSelector"
import { GeneradorControls } from "../../../components/Horario/GeneradorControls"
import { ResultadoList } from "../../../components/Horario/ResultadoList"
import { UseGetDisponiblesMaterias } from "../../materia/hook/use-get-disponibles-materias"

export default function GeneradorHorarios() {
	const { generarHorarios, cargando, resultados } = useGenerarHorario()

	const { getMateriasDisponibles, materias, loading, error } =
		UseGetDisponiblesMaterias()

	const [selectedIds, setSelectedIds] = useState([])
	const [cantidad, setCantidad] = useState(3)

	useEffect(() => {
		getMateriasDisponibles()
		setSelectedIds([])
	}, [])

	const toggleMateria = (id) => {
		setSelectedIds((prev) =>
			prev.includes(id) ? prev.filter((mId) => mId !== id) : [...prev, id]
		)
	}

	const handleCalcular = () => {
		if (selectedIds.length === 0)
			return alert("Seleccioná al menos una materia")
		generarHorarios(selectedIds, cantidad)
	}

	return (
		<div className="flex flex-col p-6 space-y-6">
			<h2 className="text-2xl font-bold text-white text-center">
				Gestion Horarios
			</h2>
			<MateriaSelector
				materias={materias}
				selectedIds={selectedIds}
				onToggle={toggleMateria}
				cargando={loading}
			/>

			<GeneradorControls
				cantidad={cantidad}
				setCantidad={setCantidad}
				onCalcular={handleCalcular}
				cargando={cargando}
				disabled={selectedIds.length === 0}
			/>

			<ResultadoList resultados={resultados} cargando={cargando} />
		</div>
	)
}
