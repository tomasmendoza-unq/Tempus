import { useEffect, useState } from "react"
import { MateriaSelector } from "../../materia/components/materiaSelector/MateriaSelector"
import { ResultadoList } from "../../../components/Horario/ResultadoList"
import { UseGetDisponiblesMaterias } from "../../materia/hook/use-get-disponibles-materias"
import { usePostHorarioCompatible } from "../hook/use-post-horario-compatible"
import { FormHorario } from "../components/form/FormHorario"
import { useFormData } from "../../../shared/hooks/use-form-data"
import { toast } from "react-toastify"

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

	useEffect(() => {
		if (error) toast.error(error)
		if (errorGenerar) toast.error(errorGenerar)
	}, [error, errorGenerar])

	const toggleMateria = (id) => {
		const nuevos = formData.selectedIds.includes(id)
			? formData.selectedIds.filter((mId) => mId !== id)
			: [...formData.selectedIds, id]
		setField("selectedIds", nuevos)
	}

	const handleCalcular = async (e) => {
		e.preventDefault()
		if (formData.selectedIds.length === 0) {
			toast.info("Seleccioná al menos una materia")
			return
		}

		const response = await generarHorarios(
			formData.selectedIds,
			Number(formData.cantidad)
		)
		setResultados(response)
		toast.success("Horarios generados con éxito")
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
