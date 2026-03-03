export const estadoInicialMateria = {
  materias: [],
  materia: null,
  cargando: false,
  error: null,
  formMateria: {
    materiaNombre: "",
    correlativas: "",
  },
  materiasSeleccionadas: [],
}

export const materiaReducer = (state, action) => {
  switch (action.type) {
    case "TRAER_MATERIAS_SOLICITUD":
      return {
        ...state,
        cargando: true,
        error: null,
      }
    case "TRAER_MATERIAS_EXITO": {
      const idsSeleccionadas = new Set(
        state.materiasSeleccionadas.map((m) => m.materiaId)
      )
      return {
        ...state,
        cargando: false,
        materias: action.payload.filter(
          (m) => !idsSeleccionadas.has(m.materiaId)
        ),
      }
    }
    case "TRAER_MATERIAS_FALLO":
      return {
        ...state,
        cargando: false,
        error: action.payload,
      }

    case "TRAER_MATERIA_SOLICITUD":
      return {
        ...state,
        cargando: true,
        error: null,
      }
    case "TRAER_MATERIA_EXITO":
      return {
        ...state,
        cargando: false,
        materia: action.payload,
      }
    case "TRAER_MATERIA_FALLO":
      return {
        ...state,
        cargando: false,
        error: action.payload,
      }
    case "ACTUALIZAR_FORM_MATERIA":
      return {
        ...state,
        formMateria: {
          ...state.formMateria,
          [action.payload.atributo]: action.payload.valor,
        },
      }
    case "LIMPIAR_FORM_MATERIA":
      return {
        ...state,
        formMateria: {
          materiaNombre: "",
          correlativas: "",
        },
      }
    case "MATERIA_CREADA_EXITO":
      return {
        ...state,
        cargando: false,
        error: null,
      }

    case "AGREGAR_MATERIA_SELECCIONADA":
      return {
        ...state,
        materiasSeleccionadas: [...state.materiasSeleccionadas, action.payload],
        materias: state.materias.filter(
          (m) => m.materiaId !== action.payload.materiaId
        ),
      }
    case "ELIMINAR_MATERIA_SELECCIONADA":
      return {
        ...state,
        materiasSeleccionadas: state.materiasSeleccionadas.filter(
          (m) => m.materiaId !== action.payload.materiaId
        ),
        materias: [...state.materias, action.payload],
      }

    case "LIMPIAR_MATERIAS_SELECCIONADAS":
      return {
        ...state,
        materiasSeleccionadas: [],
      }

    default:
      return state
  }
}
