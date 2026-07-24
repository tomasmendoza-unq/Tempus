import { apiClient } from "../../../core/api/apiCRUD"
import { AUTH_ENDPOINTS } from "../api/constants/authEndpoints"
import { setToken } from "./token.service"

export const register = async (request) => {
	try {
		const response = await apiClient.post(AUTH_ENDPOINTS.REGISTER, request)

		const token = response.token

		setToken(token)

		return { ok: true, data: response, token }
	} catch (e) {
		return {
			ok: false,
			error: e || "Error al registrar usuario",
		}
	}
}
