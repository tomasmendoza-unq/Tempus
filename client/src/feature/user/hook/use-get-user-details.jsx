import { useState } from "react"
import { getUserService } from "../service/get-user.service"

export const useGetUserDetails = () => {
	const [userDetails, setUserDetails] = useState(null)
	const [loading, setLoading] = useState(true)
	const [error, setError] = useState(null)

	const fetchUserDetails = async () => {
		setLoading(true)
		setError(null)
		const response = await getUserService()
		if (response.error) {
			setError(response.error)
		} else {
			setUserDetails(response.data)
		}
		setLoading(false)
	}

	return { userDetails, loading, error, fetchUserDetails }
}
