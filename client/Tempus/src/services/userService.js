import { API, API_ROUTES } from "../../constants"
import { createApi } from "../helpers/apiCRUD"

const api = createApi(API.BASE_URL)

export const traerPerfilService = () =>
    api
        .get(API_ROUTES.TRAER_PERFIL)
        .then((res) => res)
        .catch((error) => {
            throw error
    })

export const anotarseAComisionesService = (comisionesId) =>
  api
    .post(API_ROUTES.ANOTARSE_A_COMISIONES(comisionesId))
    .then((res) => res)
    .catch((error) => {
      throw error
    })