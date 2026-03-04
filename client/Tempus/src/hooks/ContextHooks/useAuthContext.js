import { createContext, useContext, useReducer, useEffect } from "react";

const AuthContext = createContext();


const initialState = {
  usuario: null,
  token: localStorage.getItem("token") || null,
  isAuthenticated: !!localStorage.getItem("token"),
  cargando: false,
  error: null,
};


function authReducer(state, action) {
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
      return {
        ...initialState,
        token: null,
        isAuthenticated: false,
      };
    default:
      return state;
  }
}

// 3. Provider
export const AuthProvider = ({ children }) => {
  const [state, dispatch] = useReducer(authReducer, initialState);

  // Funciones que disparan las acciones (usadas por useAuth.jsx)
  const fetchAuthRequest = () => dispatch({ type: "FETCH_AUTH_REQUEST" });
  
  const fetchAuthSuccess = (usuario) => 
    dispatch({ type: "FETCH_AUTH_SUCCESS", payload: usuario });
    
  const fetchAuthFailure = (error) => 
    dispatch({ type: "FETCH_AUTH_FAILURE", payload: error });

  const logout = () => {
    localStorage.removeItem("token");
    dispatch({ type: "LOGOUT" });
  };

  return (
    <AuthContext.Provider 
      value={{ 
        ...state, 
        fetchAuthRequest, 
        fetchAuthSuccess, 
        fetchAuthFailure, 
        logout 
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

// 4. Hook personalizado para consumir el contexto
export const useAuthContext = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuthContext debe usarse dentro de un AuthProvider");
  }
  return context;
};