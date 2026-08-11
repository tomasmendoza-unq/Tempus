import { useState } from "react"
import { getDisponiblesMateriasService } from "../services/get-disponibles-materias.service"

export const UseGetDisponiblesMaterias = () => {
	const [materias, setMaterias] = useState([])
	const [loading, setLoading] = useState(false)
	const [error, setError] = useState(null)

	const getMateriasDisponibles = async () => {
		setLoading(true)
		setError(null)

		const response = await getDisponiblesMateriasService()

		if (!response.ok) {
			setError(response.error)
			return
		}
		setLoading(false)
		setMaterias(response.data)
	}

	return {
		materias,
		getMateriasDisponibles,
		setMaterias,
		loading,
		error,
	}
}
