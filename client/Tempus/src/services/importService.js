import { API_ROUTES } from "../../constants"
import { apiClient as api } from "../core/api/apiCRUD"


export const previewImportService = (formData) =>
  api
    .post(API_ROUTES.PREVIEW_IMPORT, formData)
    .then((res) => res)
    .catch((error) => {
      throw error;
    });
