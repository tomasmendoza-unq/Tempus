import { useState } from "react"
import FormAgregarMateria from "../components/Admin/FormAgregarMateria"
import MostrarMateria from "../components/Admin/MostrarMateria"
import { useFormMateria } from "../hooks/useMateria"

export default function Materias() {
  const { clearFormMateria } = useFormMateria()

  const [isAgregarMateriaOpen, setIsAgregarMateriaOpen] = useState(true)
  return (
    <div className="flex flex-col p-4 space-y-4">
      <div className="flex justify-center">
        <div className="inline-flex rounded-lg border border-gray-300 bg-white shadow-sm overflow-hidden">
          <button
            onClick={() => {
              setIsAgregarMateriaOpen(true)
              clearFormMateria()
            }}
            className={`px-4 py-2 text-sm font-medium transition focus:outline-none focus:ring-2 focus:ring-blue-200 ${
              isAgregarMateriaOpen
                ? "bg-blue-500 text-white"
                : "bg-white text-gray-700"
            } border-r border-gray-300`}
          >
            Agregar Materia
          </button>
          <button
            onClick={() => {
              setIsAgregarMateriaOpen(false)
              clearFormMateria()
            }}
            className={`px-4 py-2 text-sm font-medium transition focus:outline-none focus:ring-2 focus:ring-blue-200 ${
              !isAgregarMateriaOpen
                ? "bg-blue-500 text-white"
                : "bg-white text-gray-700"
            }`}
          >
            Mostrar Materia
          </button>
        </div>
      </div>
      {isAgregarMateriaOpen ? (
        <div className="space-y-2">
          <h2 className="text-xl font-semibold">Cargar materia</h2>
          <FormAgregarMateria />
        </div>
      ) : (
        <div className="space-y-2">
          <h2 className="text-xl font-semibold">Buscar materia</h2>
          <MostrarMateria />
        </div>
      )}
    </div>
  )
}
