export const initialState = {
  usuario: null,
  isAuthenticated: !!localStorage.getItem("token"),
  cargando: false,
  error: null,
};

export function authReducer(state, action) {
  switch (action.type) {
    case "FETCH_AUTH_REQUEST":
      return { ...state, cargando: true, error: null };
    case "FETCH_AUTH_SUCCESS":
      return {
        ...state,
        cargando: false,
        isAuthenticated: true,
        usuario: action.payload,
        error: null,
      };
    case "FETCH_AUTH_FAILURE":
      return {
        ...state,
        cargando: false,
        isAuthenticated: false,
        usuario: null,
        error: action.payload,
      };
    case "LOGOUT":
      return { ...initialState, isAuthenticated: false };
    default:
      return state;
  }
}