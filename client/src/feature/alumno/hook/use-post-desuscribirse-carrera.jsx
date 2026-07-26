import { useState } from "react"
import { desuscribirseCarreraService } from "../service/post-desuscribirse-carrera.service"

export const UsePostDesuscribirseCarrera = () => {
	const [loading, setLoading] = useState(false)
	const [error, setError] = useState(null)

	const desuscribirseCarrera = async (idCarrera) => {
		setLoading(true)
		setError(null)

		const response = await desuscribirseCarreraService(idCarrera)

		if (!response.ok) {
			setError(response.error)
			setLoading(false)
			return null
		}

		setLoading(false)
		return response.data
	}

	return {
		desuscribirseCarrera,
		loading,
		error,
	}
}
