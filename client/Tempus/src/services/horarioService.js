import { API_ROUTES } from "../../constants"
import { apiClient as api } from "../core/api/apiCRUD"

export const generarHorarioCompatibleService = (formData) =>
    api
        .post(API_ROUTES.GENERAR_HORARIO_COMPATIBLE, formData)
        .then((res) => res) 
        .catch((error) => {
            throw error
    })
