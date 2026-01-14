import { useContext } from "react"
import { MateriaContext } from "./MateriaContext"

export function useMateriaContext() {
  const context = useContext(MateriaContext)
  if (!context) {
    throw new Error("useMateriaContext must be used within MateriaProvider")
  }
  return context
}
