import { useContext } from "react"
import { ComisionContext } from "../../contexts/ComisionContext"

export function useComisionContext() {
  const context = useContext(ComisionContext)

  if (!context) {
    throw new Error("useComisionContext must be used within ComisionProvider")
  }

  const { dispatch, comision, step, materiaSeleccionada, cargando } = context
  const estadoComision = { comision, step, materiaSeleccionada }

  const setComision = (payload) => {
    dispatch({ type: "SET_COMISION_STATE", payload })
  }

  const updateStep = (nextStep) => {
    dispatch({ type: "ACTUALIZAR_STEP", payload: nextStep })
  }

  const setMateriaSeleccionada = (materia) => {
    dispatch({ type: "SET_MATERIA_SELECCIONADA", payload: materia })
  }

  const setHorariosComision = (horarios) => {
    dispatch({ type: "SET_HORARIOS_COMISION", payload: horarios })
  }

  const clearFormComision = () => {
    dispatch({ type: "LIMPIAR_FORM_COMISION" })
  }

  const setCargando = (valor) => {
    dispatch({ type: "SET_CARGANDO", payload: valor })
  }

  const cargarComisionCompleta = (datos) => {
    dispatch({ type: "CARGAR_COMISION_COMPLETA", payload: datos })
  }

  return {
    comision: estadoComision,
    step,
    materiaSeleccionada,
    cargando,
    setComision,
    updateStep,
    setMateriaSeleccionada,
    setHorariosComision,
    clearFormComision,
    setCargando,
    cargarComisionCompleta,
  }
}
