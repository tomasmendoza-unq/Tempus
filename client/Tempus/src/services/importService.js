import { API, API_ROUTES } from "../../constants"
import { createApi } from "../helpers/apiCRUD"

const api = createApi(API.BASE_URL)


export const previewImportService = (formData) =>
  api
    .post(API_ROUTES.PREVIEW_IMPORT, formData)
    .then((res) => res)
    .catch((error) => {
      throw error;
    });