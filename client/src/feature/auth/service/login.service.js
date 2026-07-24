import { apiClient } from "../../../core/api/apiCRUD"
import { AUTH_ENDPOINTS } from "../api/constants/authEndpoints"
import { setToken } from "./token.service"

export const login = async (request) => {
	try {
		const response = await apiClient.post(AUTH_ENDPOINTS.LOGIN, request)

		const { token, ...user } = response

		setToken({ user, token })

		return { ok: true, data: user, token }
	} catch (error) {
		console.log(error)
		return { ok: false, error: error }
	}
}
