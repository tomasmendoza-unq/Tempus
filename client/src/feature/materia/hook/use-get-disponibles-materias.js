import { useState } from "react"
import { getDisponiblesMateriasService } from "../services/get-disponibles-materias.service"

export const useGetDisponiblesMaterias = () => {
	const [materias, setMaterias] = useState([])
	const [isLoading, setIsLoading] = useState(false)
	const [error, setError] = useState(null)

	const fetch = async () => {
		try {
			setIsLoading(true)
			const data = await getDisponiblesMateriasService()
			setMaterias(data)
		} catch (error) {
			setError(error)
		} finally {
			setIsLoading(false)
		}
	}

	return {
		materias,
		fetch,
		setMaterias,
		isLoading,
		error,
	}
}
