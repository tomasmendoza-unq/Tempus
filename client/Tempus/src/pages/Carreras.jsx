import CarreraForm from "../components/Carrera/CarreraForm"
import MateriasSelector from "../components/Materia/MateriasSelector"
import MateriasSeleccionadas from "../components/Materia/MateriasSeleccionadas"
import { useMateriaContext } from "../hooks/ContextHooks/useMateriaContext"
import { useEffect } from "react"

export default function Carreras() {
  const { agregarMateriaSeleccionada, limpiarMateriasSeleccionadas } =
    useMateriaContext()

  useEffect(() => {
    return () => limpiarMateriasSeleccionadas()
  }, [])

  return (
    <div className="min-h-screen p-4 md:p-8 flex flex-col bg-[#121212] text-white">
      <h1 className="text-3xl font-bold mb-8 text-center">
        Gestión de Carreras
      </h1>

      <div className="max-w-[1600px] mx-auto w-full flex flex-col gap-10">
        
        <div className="grid grid-cols-1 lg:grid-cols-12 gap-8 items-start">
          
          <div className="lg:col-span-3 w-full lg:sticky lg:top-8">
            <div className="bg-[#1e1e1e] p-1 rounded-2xl shadow-2xl border border-white/5">
              <CarreraForm />
            </div>
          </div>

          <div className="lg:col-span-9 w-full">
            <div className="bg-[#1e1e1e] rounded-2xl p-4 md:p-6 shadow-inner border border-white/5">
              <div className="max-h-[70vh] overflow-y-auto overflow-x-hidden pr-2 custom-scrollbar">
                <MateriasSelector onClick={agregarMateriaSeleccionada} />
              </div>
            </div>
            <p className="text-gray-500 text-xs mt-3 text-center italic">
              Desliza para explorar el catálogo de materias
            </p>
          </div>
        </div>

        <div className="bg-white rounded-2xl p-6 md:p-8 shadow-2xl text-gray-900">
          <MateriasSeleccionadas />
        </div>
      </div>
    </div>
  )
}