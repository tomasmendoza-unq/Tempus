import { apiClient } from "../../../core/api/apiCRUD"
import { CARRERA_ENDPOINTS } from "../api/constants/carreraEndpoints"

export const getCarreras = async () => {
	return apiClient.get(CARRERA_ENDPOINTS.GET_CARRERAS)
}
