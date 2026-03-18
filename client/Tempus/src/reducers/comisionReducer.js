export const estadoInicialComision = {
  comision: {
    comisionId: null,
    materiaId: null,
    horarios: [],
  },
  step: 0,
  materiaSeleccionada: null,
  cargando: false,
}

export const comisionReducer = (state, action) => {
  switch (action.type) {
    case "SET_COMISION_STATE":
      if (typeof action.payload === "function") {
        return action.payload(state)
      }
      return action.payload

    case "ACTUALIZAR_STEP":
      return {
        ...state,
        step: action.payload,
      }

    case "SET_MATERIA_SELECCIONADA":
      return {
        ...state,
        materiaSeleccionada: action.payload,
        comision: {
          ...state.comision,
          materiaId: action.payload?.materiaId ?? null,
        },
      }

    case "SET_HORARIOS_COMISION":
      return {
        ...state,
        comision: {
          ...state.comision,
          horarios: action.payload,
        },
      }

    case "SET_CARGANDO":
      return {
        ...state,
        cargando: action.payload,
      }

    case "CARGAR_COMISION_COMPLETA":
      return {
        ...state,
        comision: action.payload.comision,
        materiaSeleccionada: action.payload.materiaSeleccionada,
        step: action.payload.step ?? 0,
      }

    case "LIMPIAR_FORM_COMISION":
      return estadoInicialComision

    default:
      return state
  }
}
