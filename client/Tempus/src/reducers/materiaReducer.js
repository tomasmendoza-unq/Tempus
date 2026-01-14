export const estadoInicialMateria = {
  materias: [],
  materia: null,
  loading: false,
  error: null,
  formMateria: {
    materiaNombre: "",
    correlativas: [],
  },
}

export const materiaReducer = (state, action) => {
  switch (action.type) {
    case "FETCH_MATERIAS_REQUEST":
      return {
        ...state,
        loading: true,
        error: null,
      }
    case "FETCH_MATERIAS_SUCCESS":
      return {
        ...state,
        loading: false,
        materias: action.payload,
      }
    case "FETCH_MATERIAS_FAILURE":
      return {
        ...state,
        loading: false,
        error: action.payload,
      }

    case "FETCH_MATERIA_REQUEST":
      return {
        ...state,
        loading: true,
        error: null,
      }
    case "FETCH_MATERIA_SUCCESS":
      return {
        ...state,
        loading: false,
        materia: action.payload,
      }
    case "FETCH_MATERIA_FAILURE":
      return {
        ...state,
        loading: false,
        error: action.payload,
      }
    case "UPDATE_FORM_MATERIA":
      return {
        ...state,
        formMateria: {
          ...state.formMateria,
          materiaNombre: action.payload.materiaNombre,
          correlativas: action.payload.correlativas,
        },
      }
    case "CLEAR_FORM_MATERIA":
      return {
        ...state,
        formMateria: {
          materiaNombre: "",
          correlativas: [],
        },
      }
    case "MATERIA_CREADA_EXITO":
      return {
        ...state,
        loading: false,
        error: null,
      }

    default:
      return state
  }
}
