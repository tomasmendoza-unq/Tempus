import { API, API_ROUTES } from "../../constants"
import { createApi } from "../core/api/apiCRUD"

const api = createApi(API.BASE_URL)

export const generarHorarioCompatibleService = (formData) =>
    api
        .post(API_ROUTES.GENERAR_HORARIO_COMPATIBLE, formData)
        .then((res) => res) 
        .catch((error) => {
            throw error
    })
