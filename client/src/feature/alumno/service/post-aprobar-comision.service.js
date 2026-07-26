import { apiClient } from "../../../core/api/apiCRUD"
import { ALUMNO_ENDPOINTS } from "../api/alumnoEndpoints"

export const aprobarComisionService = async (comisionId) => {
	return await apiClient.post(
		ALUMNO_ENDPOINTS.POST_APROBAR_COMISION(comisionId)
	)
}
