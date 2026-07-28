import { useEffect, useState } from "react"
import { MateriaSelector } from "../../materia/components/materiaSelector/MateriaSelector"
import { GeneradorControls } from "../../../components/Horario/GeneradorControls"
import { ResultadoList } from "../../../components/Horario/ResultadoList"
import { UseGetDisponiblesMaterias } from "../../materia/hook/use-get-disponibles-materias"
import { usePostHorarioCompatible } from "../hook/use-post-horario-compatible"
import { FormHorario } from "../components/form/FormHorario"
import { useFormData } from "../../../shared/hooks/use-form-data"

export default function GenerarHorario() {
	const [resultados, setResultados] = useState([])

	const {
		generarHorarios,
		loading: loadingGenerar,
		error: errorGenerar,
	} = usePostHorarioCompatible()

	const { getMateriasDisponibles, materias, loading, error } =
		UseGetDisponiblesMaterias()

	const { formData, handleChange, setField } = useFormData({
		selectedIds: [],
		cantidad: 3,
	})
	useEffect(() => {
		getMateriasDisponibles()
		setField("selectedIds", [])
	}, [])

	const toggleMateria = (id) => {
		const nuevos = formData.selectedIds.includes(id)
			? formData.selectedIds.filter((mId) => mId !== id)
			: [...formData.selectedIds, id]
		setField("selectedIds", nuevos)
	}

	const handleCalcular = async (e) => {
		e.preventDefault()
		if (formData.selectedIds.length === 0)
			return alert("Seleccioná al menos una materia")
		const response = await generarHorarios(
			formData.selectedIds,
			Number(formData.cantidad)
		)
		setResultados(response)
	}

	return (
		<main className="flex flex-col p-6 space-y-6">
			<MateriaSelector
				materias={materias}
				selectedIds={formData.selectedIds}
				onToggle={toggleMateria}
				cargando={loading}
			/>
			<FormHorario
				formData={formData}
				handleChange={handleChange}
				onSubmit={handleCalcular}
				loading={loadingGenerar}
			/>

			<ResultadoList resultados={resultados} cargando={loadingGenerar} />
		</main>
	)
}
