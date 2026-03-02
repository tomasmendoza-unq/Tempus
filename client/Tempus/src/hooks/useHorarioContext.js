import { useContext } from "react"
import { HorarioContext } from "../contexts/HorarioContext" 
export function useHorarioContext() {
  const context = useContext(HorarioContext)

  if (!context) {
    throw new Error("useHorarioContext must be used within HorarioProvider")
  }

  const { dispatch, cargando, error, resultados, formHorario } = context

  // --- Acciones para Generar Horarios ---
  const fetchHorarioRequest = () => {
    dispatch({ type: "GENERAR_HORARIO_SOLICITUD" })
  }

  const fetchHorarioSuccess = (data) => {
    dispatch({ type: "GENERAR_HORARIO_EXITO", payload: data })
  }

  const fetchHorarioFailure = (error) => {
    dispatch({ type: "GENERAR_HORARIO_FALLO", payload: error })
  }

  // --- Acciones para el Formulario del Generador ---
  const updateFormHorario = (atributo, valor) => {
    dispatch({ type: "ACTUALIZAR_FORM_HORARIO", payload: { atributo, valor } })
  }

  const clearFormHorario = () => {
    dispatch({ type: "LIMPIAR_FORM_HORARIO" })
  }

  return {
    cargando,
    error,
    resultados,
    formHorario,
    fetchHorarioRequest,
    fetchHorarioSuccess,
    fetchHorarioFailure,
    updateFormHorario,
    clearFormHorario,
  }
}