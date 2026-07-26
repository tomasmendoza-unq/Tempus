import { useState } from "react"

export const usePostAction = (service) => {
	const [error, setError] = useState(null)
	const [loading, setLoading] = useState(false)

	const ejecutar = async (...args) => {
		setLoading(true)
		setError(null)

		const response = await service(...args)

		if (!response.ok) {
			setError(response.error)
			setLoading(false)
			return null
		}

		setLoading(false)
		return response.data
	}

	return { ejecutar, loading, error }
}
