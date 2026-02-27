import { useContext } from "react"
import { MateriaContext } from "../contexts/MateriaContext"

export function useMateriaContext() {
  const context = useContext(MateriaContext)

  if (!context) {
    throw new Error("useMateriaContext must be used within MateriaProvider")
  }

  const { dispatch, cargando, error, materias, materia, formMateria } = context

  const fetchMateriasRequest = () => {
    dispatch({ type: "TRAER_MATERIAS_SOLICITUD" })
  }

  const fetchMateriasSuccess = (materias) => {
    dispatch({ type: "TRAER_MATERIAS_EXITO", payload: materias })
  }

  const fetchMateriasFailure = (error) => {
    dispatch({ type: "TRAER_MATERIAS_FALLO", payload: error })
  }

  const fetchMateriaRequest = () => {
    dispatch({ type: "TRAER_MATERIA_SOLICITUD" })
  }

  const fetchMateriaSuccess = (materia) => {
    dispatch({ type: "TRAER_MATERIA_EXITO", payload: materia })
  }

  const fetchMateriaFailure = (error) => {
    dispatch({ type: "TRAER_MATERIA_FALLO", payload: error })
  }

  const updateFormMateria = (atributo, valor) => {
    dispatch({ type: "ACTUALIZAR_FORM_MATERIA", payload: { atributo, valor } })
  }

  const clearFormMateria = () => {
    dispatch({ type: "LIMPIAR_FORM_MATERIA" })
  }

  const materiaCreadaConExito = () => {
    dispatch({ type: "MATERIA_CREADA_EXITO" })
  }

  return {
    cargando,
    error,
    materias,
    materia,
    formMateria,
    fetchMateriasRequest,
    fetchMateriasSuccess,
    fetchMateriasFailure,
    fetchMateriaRequest,
    fetchMateriaSuccess,
    fetchMateriaFailure,
    updateFormMateria,
    clearFormMateria,
    materiaCreadaConExito,
  }
}
