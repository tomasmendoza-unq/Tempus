import { apiClient } from "../../../core/api/apiCRUD"
import { HORARIOS_ENDPOINTS } from "../api/constants/horarioEndpoints"

export const horarioCompatibleService = async (idMaterias, cantidad) => {
	return await apiClient.post(HORARIOS_ENDPOINTS.GENERAR_HORARIOS, {
		materiasIds: idMaterias,
		cantidadHorarios: cantidad,
	})
}
