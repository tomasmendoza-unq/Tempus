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
    .post(`/api/usuario/anotarse/${comisionesId.join(',')}`)
    .then((res) => res)
    .catch((error) => {
      throw error
    })