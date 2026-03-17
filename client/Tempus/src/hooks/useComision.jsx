import {
  crearComisionService,
  obtenerComisionService,
  obtenerTodasComisionesService,
} from "../services/comisionService"
import { useComisionContext } from "./ContextHooks/useComisionContext"
import { toast } from "react-toastify"

export function useComision() {
  const crearComision = async (comisionData) => {
    try {
      await crearComisionService(comisionData)
      toast.success("Comisión creada exitosamente.")
    } catch {
      toast.error("Error al crear la comisión. Intentá de nuevo.")
    }
  }

  const obtenerComision = async (comisionId) => {
    console.log("Obteniendo comisión con ID:", comisionId)
    try {
      const comision = await obtenerComisionService(comisionId)
      return comision
    } catch (error) {
      console.log({ error })
      toast.error("Error al obtener la comisión. Intentá de nuevo.")
    }
  }

  const obtenerTodasComisiones = async () => {
    try {
      const comisiones = await obtenerTodasComisionesService()
      return comisiones
    } catch {
      toast.error("Error al obtener las comisiones. Intentá de nuevo.")
    }
  }
  return { crearComision, obtenerComision, obtenerTodasComisiones }
}

export function useFormComision() {
  const { comision, setComision, clearFormComision } = useComisionContext()

  return {
    comision,
    setComision,
    clearFormComision,
  }
}
