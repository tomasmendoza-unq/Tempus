export const initialState = {
  perfil: null,
  cargando: false,
  error: null,
};

export function userReducer(state, action) {
  switch (action.type) {
    case "FETCH_USER_REQUEST":
      return { ...state, cargando: true, error: null };
    case "FETCH_USER_SUCCESS":
      return { ...state, cargando: false, perfil: action.payload, error: null };
    case "FETCH_USER_FAILURE":
      return { ...state, cargando: false, error: action.payload };
    default:
      return state;
  }
}