import { toast } from "react-toastify"
import { crearCarreraService, recuperarCarrerasService } from "../services/carreraService"

const useCarrera = () => {
  const crearCarrera = async (formData) => {
    try {
      const response = await crearCarreraService(formData)

      toast.success(response.message || "Carrera creada con éxito")
      return response
    } catch (error) {
      toast.error(error.message || "Error al crear la carrera")
    }
  }

  const recuperarCarreras = async () => {
    try {
      const response = await recuperarCarrerasService()

      return response
    } catch (error) {
      toast.error(error.message || "Error al recuperar las carreras")
    }
  }

  return { crearCarrera, recuperarCarreras }
}
export default useCarrera
