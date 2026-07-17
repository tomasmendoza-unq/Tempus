import { createContext, useContext, useReducer } from "react"
import {
  comisionReducer,
  estadoInicialComision,
} from "../reducers/comisionReducer"

export const ComisionContext = createContext(null)

export function ComisionProvider({ children }) {
  const [state, dispatch] = useReducer(comisionReducer, estadoInicialComision)

  return (
    <ComisionContext.Provider
      value={{
        ...state,
        dispatch,
      }}
    >
      {children}
    </ComisionContext.Provider>
  )
}

export function useComisionContext() {
  const context = useContext(ComisionContext)

  if (!context) {
    throw new Error("useComisionContext must be used within ComisionProvider")
  }

  return context
}
