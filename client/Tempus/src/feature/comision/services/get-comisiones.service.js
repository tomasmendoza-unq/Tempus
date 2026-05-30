import { apiClient } from "../../../core/api/apiCRUD"
import { COMISION_ENDPOINTS } from "../constants/comisionEndpoints.js"

export const getComisionesService = async () => {
	const response = await apiClient.get(COMISION_ENDPOINTS.GET_ALL_COMISIONES())
	console.log("Response from getComisionesService:", response)
	return response.content || []
}
