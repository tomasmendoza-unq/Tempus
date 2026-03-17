import { API, API_ROUTES } from "../../constants"
import { createApi } from "../helpers/apiCRUD"

const api = createApi(API.BASE_URL)

export const actualizarHorarioService = (formData) =>
  api
    .patch(API_ROUTES.ACTUALIZAR_HORARIO, formData)
    .then((res) => res)
    .catch((error) => {
      throw error
    })

export const eliminarHorarioService = (horarioId) =>
  api
    .delete(API_ROUTES.ELIMINAR_HORARIO(horarioId))
    .then((res) => res)
    .catch((error) => {
      throw error
    })
