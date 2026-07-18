import { toast } from "react-toastify"
import { 
  crearCarreraService, 
  recuperarCarrerasService, 
  crearCarreraBulkService 
} from "../services/carreraService"

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

  const crearCarreraBulk = async (bulkData) => {
    try {
      const response = await crearCarreraBulkService(bulkData)
      toast.success("¡Oferta académica cargada con éxito!")
      return response
    } catch (error) {
      toast.error(error.response?.data?.message || "Error al procesar la carga masiva")
      console.log(error)
      throw error 
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

  return { crearCarrera, recuperarCarreras, crearCarreraBulk }
}

export default useCarrera