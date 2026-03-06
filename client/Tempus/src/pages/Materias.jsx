import { useState } from "react"
import FormAgregarMateria from "../components/Admin/FormAgregarMateria"
import MostrarMateria from "../components/Admin/MostrarMateria"
import { useFormMateria } from "../hooks/useMateria"

export default function Materias() {
  const { clearFormMateria } = useFormMateria()

  const [isAgregarMateriaOpen, setIsAgregarMateriaOpen] = useState(true)
  return (
    <div className="flex flex-col p-6 gap-6 max-w-2xl mx-auto w-full">
      <div className="flex justify-center">
        <div className="inline-flex rounded-xl border border-gray-200 bg-white shadow-sm overflow-hidden">
          <button
            onClick={() => {
              setIsAgregarMateriaOpen(true)
              clearFormMateria()
            }}
            className={`px-6 py-2.5 text-sm font-medium transition focus:outline-none ${
              isAgregarMateriaOpen
                ? "bg-red-950 text-white"
                : "bg-white text-gray-600 hover:bg-gray-50"
            } border-r border-gray-200`}
          >
            Agregar materia
          </button>
          <button
            onClick={() => {
              setIsAgregarMateriaOpen(false)
              clearFormMateria()
            }}
            className={`px-6 py-2.5 text-sm font-medium transition focus:outline-none ${
              !isAgregarMateriaOpen
                ? "bg-red-950 text-white"
                : "bg-white text-gray-600 hover:bg-gray-50"
            }`}
          >
            Buscar materia
          </button>
        </div>
      </div>
      {isAgregarMateriaOpen ? <FormAgregarMateria /> : <MostrarMateria />}
    </div>
  )
}
