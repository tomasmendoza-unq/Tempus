import { apiClient } from "../../../core/api/apiCRUD"
import { AUTH_ENDPOINTS } from "../api/constants/authEndpoints"
import { setToken } from "./token.service"

export const register = async (request) => {
	try {
		const response = await apiClient.post(AUTH_ENDPOINTS.REGISTER, request)

		const { token, ...user } = response

		setToken({ user, token })

		return { ok: true, data: response, token: token }
	} catch (e) {
		return {
			ok: false,
			error: e.error || "Error al registrar usuario",
		}
	}
}
