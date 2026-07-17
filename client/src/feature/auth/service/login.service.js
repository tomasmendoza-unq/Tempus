import { apiClient } from "../../../core/api/apiCRUD"
import { AUTH_ENDPOINTS } from "../api/constants/authEndpoints"
import { setToken } from "./token.service"

export const login = async (request) => {
	const response = await apiClient.post(AUTH_ENDPOINTS.LOGIN, request)

	setToken(response.token)

	return response
}
