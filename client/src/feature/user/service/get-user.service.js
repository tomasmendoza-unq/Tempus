import { apiClient } from "../../../core/api/apiCRUD"
import { USER_ENDPOINTS } from "../api/userEndpoints.js"

export const getUserService = async () => {
	try {
		const response = await apiClient.get(USER_ENDPOINTS.GET_USER)
		return { ok: true, data: response }
	} catch (error) {
		throw { ok: false, error: error || "Error al obtener el usuario" }
	}
}
