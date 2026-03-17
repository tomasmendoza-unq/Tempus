import { API, API_ROUTES } from "../../constants"
import { createApi } from "../helpers/apiCRUD"

const api = createApi(API.BASE_URL)

export const crearComisionService = (formData) =>
  api
    .post(API_ROUTES.CREAR_COMISION, formData)
    .then((res) => res)
    .catch((error) => {
      throw error
    })

export const obtenerComisionService = (comisionId) =>
  api
    .get(API_ROUTES.OBTENER_COMISION(comisionId))
    .then((res) => res)
    .catch((error) => {
      throw error
    })

export const obtenerTodasComisionesService = () =>
  api
    .get(API_ROUTES.OBTENER_TODAS_COMISIONES)
    .then((res) => res)
    .catch((error) => {
      throw error
    })

export const editarComisionService = (comisionId, formData) =>
  api
    .put(API_ROUTES.ACTUALIZAR_COMISION(comisionId), formData)
    .then((res) => res)
    .catch((error) => {
      throw error
    })

export const eliminarComisionService = (comisionId) =>
  api
    .delete(API_ROUTES.ELIMINAR_COMISION(comisionId))
    .then((res) => res)
    .catch((error) => {
      throw error
    })
