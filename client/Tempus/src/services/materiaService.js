import { API, API_ROUTES } from "../../constants"
import { createApi } from "../helpers/apiCRUD"

const api = createApi(API.BASE_URL)

export const crearMateriaService = (formData) =>
  api
    .post(API_ROUTES.CREAR_MATERIA, formData)
    .then((res) => res)
    .catch((error) => {
      throw error
    })

export const traerMateriaService = (id) =>
  api
    .get(API_ROUTES.TRAER_MATERIA(id))
    .then((res) => res)
    .catch((error) => {
      throw error
    })

export const traerTodasMateriasService = () =>
  api
    .get(API_ROUTES.TRAER_TODAS_MATERIAS)
    .then((res) => res)
    .catch((error) => {
      throw error
    })

export const buscarMateriaPorNombreService = (materiaNombre) =>
  api
    .get(API_ROUTES.BUSCAR_MATERIA_POR_NOMBRE(materiaNombre))
    .then((res) => res)
    .catch((error) => {
      throw error
    })
