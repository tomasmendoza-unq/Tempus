import { apiClient } from "../../../core/api/apiCRUD.js"
import { ALUMNO_ENDPOINTS } from "../api/alumnoEndpoints.js"

export const getAlumnoService = async () => {
	return apiClient.get(ALUMNO_ENDPOINTS.GET_ALUMNO)
}
