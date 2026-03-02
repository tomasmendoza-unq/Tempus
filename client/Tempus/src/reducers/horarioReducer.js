export const estadoInicialHorario = {
  resultados: [],
  cargando: false,
  error: null,
  formHorario: {
    materiasIds: [],
    cantidadHorarios: 3,
  },
};

export const horarioReducer = (state, action) => {
  switch (action.type) {
    case "GENERAR_HORARIO_SOLICITUD":
      return {
        ...state,
        cargando: true,
        error: null,
      };
    case "GENERAR_HORARIO_EXITO":
      return {
        ...state,
        cargando: false,
        resultados: action.payload,
      };
    case "GENERAR_HORARIO_FALLO":
      return {
        ...state,
        cargando: false,
        error: action.payload,
      };
    case "ACTUALIZAR_FORM_HORARIO":
      return {
        ...state,
        formHorario: {
          ...state.formHorario,
          [action.payload.atributo]: action.payload.valor,
        },
      };
    case "LIMPIAR_FORM_HORARIO":
      return {
        ...state,
        formHorario: {
          materiasIds: [],
          cantidadHorarios: 3,
        },
      };
    default:
      return state;
  }
};