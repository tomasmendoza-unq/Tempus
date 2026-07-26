import { apiClient } from "../../../core/api/apiCRUD.js"
import { CARRERA_ENDPOINTS } from "../api/constants/carreraEndpoints.js"

export const getCarrerasDisponibles = async () => {
	return await apiClient.get(CARRERA_ENDPOINTS.GET_CARRERAS_DISPONIBLES)
}
