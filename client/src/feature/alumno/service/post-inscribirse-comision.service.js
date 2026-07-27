import { apiClient } from "../../../core/api/apiCRUD"
import { ALUMNO_ENDPOINTS } from "../api/alumnoEndpoints"

export const inscribirseComisionesService = async (comisionesId) => {
	const response = await apiClient.post(ALUMNO_ENDPOINTS.POST_INSCRIBIRSE, {
		comisionesId: comisionesId,
	})

	console.log("response", response)

	return response
}
