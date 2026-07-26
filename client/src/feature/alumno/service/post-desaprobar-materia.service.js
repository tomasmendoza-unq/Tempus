import { apiClient } from "../../../core/api/apiCRUD.js"
import { ALUMNO_ENDPOINTS } from "../api/alumnoEndpoints.js"

export const desaprobarMateriaService = async (idMateria) => {
	return await apiClient.post(
		ALUMNO_ENDPOINTS.POST_DESAPROBAR_MATERIA(idMateria)
	)
}
