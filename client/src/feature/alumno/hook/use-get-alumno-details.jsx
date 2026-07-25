import { useState } from "react"
import { getAlumnoService } from "../service/get-alumno.service"

export const useGetAlumnoDetails = () => {
	const [alumnoDetails, setAlumnoDetails] = useState(null)
	const [loading, setLoading] = useState(true)
	const [error, setError] = useState(null)

	const fetchAlumnoDetails = async () => {
		setLoading(true)
		setError(null)

		const response = await getAlumnoService()

		if (!response.ok) {
			setError(response.error)
			setLoading(false)
			return
		}

		setAlumnoDetails(response.data)
		setLoading(false)
	}

	return { alumnoDetails, loading, error, fetchAlumnoDetails }
}
