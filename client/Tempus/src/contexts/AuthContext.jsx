import { createContext, useContext, useReducer } from "react";
import { authReducer, initialState } from "../reducers/authReducer";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [state, dispatch] = useReducer(authReducer, initialState);

  // Estas son las funciones que tus hooks .jsx están esperando:
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

// El hook para que useLogin y useRegistrarUsuario puedan acceder
export const useAuthContext = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuthContext debe usarse dentro de un AuthProvider");
  }
  return context;
};