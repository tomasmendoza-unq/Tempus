import { crearCarreraService } from "../services/carreraService"

const useCarrera = () => {
  const crearCarrera = async (formData) => {
    try {
      await crearCarreraService(formData)

      console.log("Carrera creada con éxito")
    } catch (error) {
      console.error("Error al crear la carrera:", error)
    }
  }
}
