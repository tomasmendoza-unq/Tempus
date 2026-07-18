import { apiClient } from "../../../core/api/apiCRUD"
import { MATERIA_ENDPOINTS } from "../constants/materiaEndpoints"

export const getMateriasByNameService = (name) => {
	return apiClient.get(MATERIA_ENDPOINTS.GET_MATERIA_BY_NAME(name))
}
