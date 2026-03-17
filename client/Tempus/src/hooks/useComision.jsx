import {
  crearComisionService,
  obtenerComisionService,
  obtenerTodasComisionesService,
  editarComisionService,
} from "../services/comisionService"

import {
  actualizarHorarioService,
  eliminarHorarioService,
} from "../services/claseHorarioService"
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
    try {
      const response = await obtenerComisionService(comisionId)
      return response
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

  const editarComision = async (comisionId, nuevaComisionId) => {
    try {
      await editarComisionService(comisionId, { materiaId: nuevaComisionId })
      toast.success("Comisión editada exitosamente.")
    } catch {
      toast.error("Error al editar la comisión. Intentá de nuevo.")
    }
  }

  const editarHorariosComision = async (horarios) => {
    try {
      if (!horarios || horarios.length === 0) {
        toast.warning("No hay horarios para actualizar.")
        return
      }

      const horariosFormateados = horarios.map((h) => ({
        id: h.id,
        dia: h.dia,
        inicio: h.horaInicio,
        fin: h.horaFin,
      }))

      await actualizarHorarioService(horariosFormateados)
      toast.success("Horarios actualizados exitosamente.")
    } catch {
      toast.error("Error al actualizar los horarios. Intentá de nuevo.")
    }
  }

  const eliminarHorarioComision = async (horarioId) => {
    try {
      await eliminarHorarioService(horarioId)
      toast.success("Horario eliminado exitosamente.")
    } catch {
      toast.error("Error al eliminar el horario. Intentá de nuevo.")
    }
  }

  return {
    crearComision,
    obtenerComision,
    obtenerTodasComisiones,
    editarComision,
    editarHorariosComision,
    eliminarHorarioComision,
  }
}

export function useFormComision() {
  const {
    comision,
    setComision,
    setMateriaSeleccionada,
    setHorariosComision,
    clearFormComision,
    cargando,
    setCargando,
    cargarComisionCompleta,
  } = useComisionContext()

  return {
    comision,
    setComision,
    setMateriaSeleccionada,
    setHorariosComision,
    clearFormComision,
    cargando,
    setCargando,
    cargarComisionCompleta,
  }
}
