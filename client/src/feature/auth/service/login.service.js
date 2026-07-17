import { apiClient } from "../../../core/api/apiCRUD"
import { AUTH_ENDPOINTS } from "../api/constants/authEndpoints"

export const login = async (request) => {
	return await apiClient.post(AUTH_ENDPOINTS.LOGIN, request)
}
