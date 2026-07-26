import { useState } from "react"
import { getCarreras } from "../service/get-carreras.service"

export const UseGetCarreras = () => {
	const [carreras, setCarreras] = useState([])
	const [loading, setLoading] = useState(true)
	const [error, setError] = useState(null)

	const fetchCarreras = async () => {
		setLoading(true)
		setError(null)
		const response = await getCarreras()

		if (!response.ok) {
			setError(response.error)
			setLoading(false)
			return
		}

		setCarreras(response.data)
		setLoading(false)
	}

	return { carreras, loading, error, fetchCarreras }
}
