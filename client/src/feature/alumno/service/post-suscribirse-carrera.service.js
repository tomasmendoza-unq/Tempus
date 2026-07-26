import { apiClient } from "../../../core/api/apiCRUD"
import { ALUMNO_ENDPOINTS } from "../api/alumnoEndpoints"

export const suscribirseCarrera = async (idCarrera) => {
	return await apiClient.post(ALUMNO_ENDPOINTS.POST_SUSCRIBIRSE_CARRERA, {
		idCarrera: idCarrera,
	})
}
