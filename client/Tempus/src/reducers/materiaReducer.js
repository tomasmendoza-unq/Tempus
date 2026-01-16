export const estadoInicialMateria = {
  materias: [],
  materia: null,
  cargando: false,
  error: null,
  formMateria: {
    materiaNombre: "",
    correlativas: "",
  },
}

export const materiaReducer = (state, action) => {
  switch (action.type) {
    case "TRAER_MATERIAS_SOLICITUD":
      return {
        ...state,
        cargando: true,
        error: null,
      }
    case "TRAER_MATERIAS_EXITO":
      return {
        ...state,
        cargando: false,
        materias: action.payload,
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

    default:
      return state
  }
}
