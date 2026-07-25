import { ALUMNO_ENDPOINTS } from "../api/alumnoEndpoints"
import { apiClient } from "../../../core/api/apiCRUD.js"

export const getCarrerasDisponibles = async () => {
	return await apiClient.get(ALUMNO_ENDPOINTS.GET_CARRERAS_DISPONIBLES)
}
