import { API, API_ROUTES } from "../../constants"
import { createApi } from "../helpers/apiCRUD"

const api = createApi(API.BASE_URL)


export const previewImportService = () =>
  api
    .post(API_ROUTES.PREVIEW_IMPORT)
    .then((res) => res)
    .catch((error) => {
      throw error

    })