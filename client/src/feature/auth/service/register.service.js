import { apiClient } from "../../../core/api/apiCRUD"
import { AUTH_ENDPOINTS } from "../api/constants/authEndpoints"

export const register = (request) => {
	return apiClient.post(AUTH_ENDPOINTS.REGISTER, request)
}
