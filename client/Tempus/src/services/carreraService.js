import { API_ROUTES } from "../../constants"
import { apiClient as api } from "../core/api/apiCRUD"

export const crearCarreraService = (formData) =>
  api
    .post(API_ROUTES.CREAR_CARRERA, formData)
    .then((res) => res)
    .catch((error) => {
      throw error
    })

export const recuperarCarrerasService = () =>
  api
    .get(API_ROUTES.RECUPERAR_CARRERAS)
    .then((res) => res)
    .catch((error) => {
      throw error

    })

export const crearCarreraBulkService = (bulkData) =>
  api
    .post(API_ROUTES.CARRERA_BULK, bulkData)
    .then((res) => res)
    .catch((error) => {
      throw error

    })
