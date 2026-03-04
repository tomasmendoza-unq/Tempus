import { createContext, useContext, useReducer } from "react";
import { userReducer, initialState } from "../reducers/userReducer";

const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [state, dispatch] = useReducer(userReducer, initialState);

  const fetchUserRequest = () => dispatch({ type: "FETCH_USER_REQUEST" });
  const fetchUserSuccess = (perfil) => dispatch({ type: "FETCH_USER_SUCCESS", payload: perfil });
  const fetchUserFailure = (error) => dispatch({ type: "FETCH_USER_FAILURE", payload: error });

  return (
    <UserContext.Provider value={{ ...state, fetchUserRequest, fetchUserSuccess, fetchUserFailure }}>
      {children}
    </UserContext.Provider>
  );
};

export const useUserContext = () => {
  const context = useContext(UserContext);
  if (!context) throw new Error("useUserContext debe usarse dentro de un UserProvider");
  return context;
};