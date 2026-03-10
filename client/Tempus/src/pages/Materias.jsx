import { useState } from "react"
import FormAgregarMateria from "../components/Admin/FormAgregarMateria"
import MostrarMateria from "../components/Admin/MostrarMateria"
import { useFormMateria } from "../hooks/useMateria"
import CorrelativasForm from "../components/Correlativas/CorrelativasForm"
import { useMateriaContext } from "../hooks/ContextHooks/useMateriaContext"

export default function Materias() {
  const { clearFormMateria } = useFormMateria()
  const { limpiarMaterias } = useMateriaContext()

  const [currentTab, setCurrentTab] = useState(0)
  return (
    <div className="flex flex-col p-6 gap-6 max-w-2xl mx-auto w-full">
      <div className="flex justify-center">
        <div className="inline-flex rounded-xl border border-gray-200 bg-white shadow-sm overflow-hidden">
          <button
            onClick={() => {
              setCurrentTab(0)
              clearFormMateria()
              limpiarMaterias()
            }}
            className={`px-6 py-2.5 text-sm font-medium transition focus:outline-none ${
              currentTab === 0
                ? "bg-red-950 text-white"
                : "bg-white text-gray-600 hover:bg-gray-50"
            } border-r border-gray-200`}
          >
            Agregar materia
          </button>
          <button
            onClick={() => {
              setCurrentTab(1)
              clearFormMateria()
              limpiarMaterias()
            }}
            className={`px-6 py-2.5 text-sm font-medium transition focus:outline-none ${
              currentTab === 1
                ? "bg-red-950 text-white"
                : "bg-white text-gray-600 hover:bg-gray-50"
            }`}
          >
            Buscar materia
          </button>
          <button
            onClick={() => {
              setCurrentTab(2)
              clearFormMateria()
              limpiarMaterias()
            }}
            className={`px-6 py-2.5 text-sm font-medium transition focus:outline-none ${
              currentTab === 2
                ? "bg-red-950 text-white"
                : "bg-white text-gray-600 hover:bg-gray-50"
            }`}
          >
            Correlativas
          </button>
        </div>
      </div>
      {currentTab === 0 && <FormAgregarMateria />}
      {currentTab === 1 && <MostrarMateria />}
      {currentTab === 2 && <CorrelativasForm />}
    </div>
  )
}
