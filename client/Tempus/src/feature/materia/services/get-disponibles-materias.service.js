import { apiClient } from "../../../core/api/apiCRUD"
import { MATERIA_ENDPOINTS } from "../constants/materiaEndpoints"

export const getDisponiblesMateriasService = () => {
	return apiClient.get(MATERIA_ENDPOINTS.GET_DISPONIBLES_MATERIAS())
}
