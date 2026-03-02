import { API, API_ROUTES } from "../../constants"
import { createApi } from "../helpers/apiCRUD"

const api = createApi(API.BASE_URL)

export const registrarUsuarioService = (formData) =>
  api
    .post(API_ROUTES.REGISTER, formData)
    .then((res) => res)
    .catch((error) => {
      throw error
    })

export const loginService = (loginData) =>
  api
    .post(API_ROUTES.LOGIN, loginData)
    .then((res) => res)
    .catch((error) => {
      throw error
    })