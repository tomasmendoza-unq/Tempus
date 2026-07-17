import { createContext, useContext, useReducer } from "react"
import {
  estadoInicialMateria,
  materiaReducer,
} from "../reducers/materiaReducer"

export const MateriaContext = createContext(null)

export function MateriaProvider({ children }) {
  const [state, dispatch] = useReducer(materiaReducer, estadoInicialMateria)

  return (
    <MateriaContext.Provider
      value={{
        ...state,
        dispatch,
      }}
    >
      {children}
    </MateriaContext.Provider>
  )
}

export function useMateriaContext() {
  const context = useContext(MateriaContext)
  if (!context) {
    throw new Error("useMateriaContext must be used within MateriaProvider")
  }
  return context
}
