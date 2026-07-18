import {
  crearMateriaService,
  traerMateriaService,
  traerTodasMateriasService,
  buscarMateriaPorNombreService,
  traerMateriasDisponiblesService,
  asociarMateriasService,
} from "../services/materiaService"
import { useMateriaContext } from "./ContextHooks/useMateriaContext"
import { toast } from "react-toastify"

export function useCrearMateria() {
  const {
    cargando,
    error,
    fetchMateriaRequest,
    fetchMateriaFailure,
    materiaCreadaConExito,
  } = useMateriaContext()

  const crearMateria = async (formData) => {
    fetchMateriaRequest()
    try {
      await crearMateriaService(formData)
      toast.success(`Materia ${formData.materiaNombre} creada con éxito`)
      materiaCreadaConExito()
    } catch (err) {
      fetchMateriaFailure(err)
      console.error("Error completo:", err)
      toast.error("Error al crear la materia")
      throw err
    }
  }

  return { crearMateria, cargando, error }
}

export function useTraerMateria() {
  const {
    cargando,
    error,
    materia,
    materias,
    fetchMateriaRequest,
    fetchMateriaSuccess,
    fetchMateriaFailure,
  } = useMateriaContext()

  const traerMateria = async (materiaId) => {
    fetchMateriaRequest()
    try {
      const data = await traerMateriaService(materiaId)

      fetchMateriaSuccess(data)
      return data
    } catch (err) {
      console.error("Error:", err)
      fetchMateriaFailure(err)
      throw err
    }
  }

  return {
    traerMateria,
    cargando,
    error,
    materia,
    materias,
  }
}

export function useTraerTodasMaterias() {
  const {
    cargando,
    error,
    materias,
    fetchMateriasRequest,
    fetchMateriasSuccess,
    fetchMateriasFailure,
  } = useMateriaContext()

  const traerMaterias = async () => {
    fetchMateriasRequest()
    try {
      const data = await traerTodasMateriasService()
      fetchMateriasSuccess(data)
      return data
    } catch (err) {
      console.error("Error:", err)
      fetchMateriasFailure(err)
      throw err
    }
  }

  return {
    traerMaterias,
    cargando,
    error,
    materias,
  }
}

export function useBuscarMateriaPorNombre() {
  const {
    cargando,
    error,
    materias,
    fetchMateriasRequest,
    fetchMateriasSuccess,
    fetchMateriasFailure,
  } = useMateriaContext()

  const buscarMateriaPorNombre = async (materiaNombre) => {
    fetchMateriasRequest()
    try {
      const data = await buscarMateriaPorNombreService(materiaNombre)
      fetchMateriasSuccess(data)
      return data
    } catch (err) {
      console.error("Error:", err)
      fetchMateriasFailure(err)
      toast.error("Error al buscar la materia")
      throw err
    }
  }

  return {
    buscarMateriaPorNombre,
    cargando,
    error,
    materias,
  }
}

export function useFormMateria() {
  const { formMateria, updateFormMateria, clearFormMateria } =
    useMateriaContext()

  return {
    formMateria,
    updateFormMateria,
    clearFormMateria,
  }
}

export function useTraerMateriasDisponibles() {
  const {
    cargando,
    error,
    materias,
    fetchMateriasRequest,
    fetchMateriasSuccess,
    fetchMateriasFailure,
  } = useMateriaContext()

  const traerMateriasDisponibles = async () => {
    fetchMateriasRequest()
    try {
      const data = await traerMateriasDisponiblesService()
      fetchMateriasSuccess(data)
      return data
    } catch (err) {
      console.error("Error:", err)
      fetchMateriasFailure(err)
      throw err
    }
  }

  return {
    traerMateriasDisponibles,
    cargando,
    error,
    materias,
  }
}

export function useAsociarMaterias() {
  const {
    cargando,
    error,
    fetchMateriaRequest,
    fetchMateriaFailure,
    materiaCreadaConExito,
  } = useMateriaContext()

  const asociarMaterias = async (materias) => {
    fetchMateriaRequest()
    try {
      await asociarMateriasService(materias)
      toast.success("Correlativas vinculadas con éxito")
      materiaCreadaConExito()
    } catch (err) {
      fetchMateriaFailure(err)
      toast.error("Error al vincular las materias")
      throw err
    }
  }

  return { asociarMaterias, cargando, error }
}
