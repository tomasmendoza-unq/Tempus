import { apiClient } from "../../../core/api/apiCRUD"
import { ALUMNO_ENDPOINTS } from "../api/alumnoEndpoints"

export const desuscribirseCarreraService = async (idCarrera) => {
	return apiClient.post(ALUMNO_ENDPOINTS.POST_DESUSCRIBIRSE_CARRERA, {
		idCarrera: idCarrera,
	})
}
