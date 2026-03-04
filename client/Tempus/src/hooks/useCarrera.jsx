import { toast } from "react-toastify"
import { crearCarreraService } from "../services/carreraService"

const useCarrera = () => {
  const crearCarrera = async (formData) => {
    try {
      const response = await crearCarreraService(formData)

      toast.success(response.message || "Carrera creada con éxito")
      return response
    } catch (error) {
      toast.error(error.message || "Error al crear la carrera")
      console.error("Error al crear la carrera:", error)
    }
  }

  return { crearCarrera }
}
export default useCarrera
