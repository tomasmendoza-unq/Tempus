import { useState } from "react"
import { getCarrerasDisponibles } from "../service/get-carreras-disponibles.service"

export const UseGetCarrerasDisponibles = () => {
	const [carrerasDisponibles, setCarrerasDisponibles] = useState([])
	const [loading, setLoading] = useState(false)
	const [error, setError] = useState(null)

	const fetchCarrerasDisponibles = async () => {
		setLoading(true)
		setError(null)

		const response = await getCarrerasDisponibles()

		if (!response.ok) {
			setError(response.error)
			setLoading(false)
			return
		}

		setCarrerasDisponibles(response.data)
		setLoading(false)
	}

	return {
		carrerasDisponibles,
		loading,
		error,
		fetchCarrerasDisponibles,
	}
}
