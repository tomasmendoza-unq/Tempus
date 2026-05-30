import { apiClient } from "../../../core/api/apiCRUD.js"
import { COMISION_ENDPOINTS } from "../constants/comisionEndpoints.js"

export const createComisionService = (formData) =>
  apiClient.post(COMISION_ENDPOINTS.CREATE_COMISION, formData)
