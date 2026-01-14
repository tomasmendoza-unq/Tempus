import {
  crearMateriaService,
  traerMateriaService,
  traerTodasMateriasService,
} from "../services/materiaService"
import { useMateriaContext } from "../contexts/MateriaContext"
import { toast } from "react-toastify"

export function useCrearMateria() {
  const { dispatch, loading, error } = useMateriaContext()

  const crearMateria = async (formData) => {
    dispatch({ type: "FETCH_MATERIA_REQUEST" })
    try {
      await crearMateriaService(formData)
      toast.success(`Materia ${formData.materiaNombre} creada con éxito`)
      dispatch({ type: "MATERIA_CREADA_EXITO" })
    } catch (err) {
      dispatch({ type: "FETCH_MATERIA_FAILURE", payload: err })
      console.error("Error completo:", err)
      toast.error("Error al crear la materia")
      throw err
    }
  }

  return { crearMateria, loading, error }
}

export function useTraerMateria() {
  const { dispatch, loading, error, materia } = useMateriaContext()

  const traerMateria = async (idMateria) => {
    dispatch({ type: "FETCH_MATERIA_REQUEST" })
    try {
      const data = await traerMateriaService(idMateria)
      dispatch({ type: "FETCH_MATERIA_SUCCESS", payload: data })
      console.log({ data })
      return data
    } catch (err) {
      console.error("Error:", err)
      dispatch({ type: "FETCH_MATERIA_FAILURE", payload: err })
      throw err
    }
  }

  return { traerMateria, loading, error, materia }
}

export function useTraerTodasMaterias() {
  const { dispatch, loading, error, materias } = useMateriaContext()

  const traerMaterias = async () => {
    dispatch({ type: "FETCH_MATERIAS_REQUEST" })
    try {
      const data = await traerTodasMateriasService()
      dispatch({ type: "FETCH_MATERIAS_SUCCESS", payload: data })
      return data
    } catch (err) {
      console.error("Error:", err)
      dispatch({ type: "FETCH_MATERIAS_FAILURE", payload: err })
      throw err
    }
  }

  return { traerMaterias, loading, error, materias }
}

export function useFormMateria() {
  const { dispatch, formMateria } = useMateriaContext()

  const updateFormMateria = (formData) => {
    dispatch({ type: "UPDATE_FORM_MATERIA", payload: formData })
  }

  return { formMateria, updateFormMateria }
}
