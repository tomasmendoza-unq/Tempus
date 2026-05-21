import { API_ROUTES } from "../../constants"
import { apiClient as api } from "../core/api/apiCRUD"

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
