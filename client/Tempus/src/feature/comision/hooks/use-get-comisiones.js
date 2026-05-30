import { useState } from "react"
import { getComisionesService } from "../services/get-comisiones.service"

export const useGetComisiones = () => {
	const [comisiones, setComisiones] = useState([])
	const [isLoading, setIsLoading] = useState(false)
	const [error, setError] = useState(null)

	const fetch = async () => {
		try {
			setIsLoading(true)
			const data = await getComisionesService()
			setComisiones(data)
		} catch (error) {
			console.error("Error fetching comisiones:", error)
			setError(error)
		} finally {
			setIsLoading(false)
		}
	}

	return {
		comisiones,
		fetch,
		isLoading,
		error,
	}
}
