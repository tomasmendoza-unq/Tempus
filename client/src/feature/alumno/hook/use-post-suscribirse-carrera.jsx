import { useState } from "react"
import { suscribirseCarrera as suscribirseService } from "../service/post-suscribirse-carrera.service"

export const UsePostSuscribirseCarrera = () => {
	const [loading, setLoading] = useState(false)
	const [error, setError] = useState(null)

	const suscribirseCarrera = async (idCarrera) => {
		setLoading(true)
		setError(null)

		const reponse = await suscribirseService(idCarrera)

		if (!reponse.ok) {
			setError(reponse.error)
			setLoading(false)
			return null
		}

		setLoading(false)
		return reponse.data
	}

	return {
		loading,
		error,
		suscribirseCarrera,
	}
}
