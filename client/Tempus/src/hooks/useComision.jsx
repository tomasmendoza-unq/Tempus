import {
  crearComisionService,
  obtenerComisionService,
  obtenerTodasComisionesService,
} from "../services/comisionService"
import { toast } from "react-toastify"

export function useComision() {
  const crearComision = async (comisionData) => {
    try {
      await crearComisionService(comisionData)
      toast.success("Comisión creada exitosamente.")
    } catch (error) {
      toast.error("Error al crear la comisión. Intentá de nuevo.")
    }
  }

  const obtenerComision = async (comisionId) => {
    try {
      const comision = await obtenerComisionService(comisionId)
      return comision
    } catch (error) {
      toast.error("Error al obtener la comisión. Intentá de nuevo.")
    }
  }

  const obtenerTodasComisiones = async () => {
    try {
      const comisiones = await obtenerTodasComisionesService()
      return comisiones
    } catch (error) {
      toast.error("Error al obtener las comisiones. Intentá de nuevo.")
    }
  }
  return { crearComision, obtenerComision, obtenerTodasComisiones }
}
