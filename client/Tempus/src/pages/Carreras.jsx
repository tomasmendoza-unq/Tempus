import CarreraForm from "../components/Carrera/CarreraForm"
import MateriasSelector from "../components/Materia/MateriasSelector"
import MateriasSeleccionadas from "../components/Materia/MateriasSeleccionadas"
import { useMateriaContext } from "../hooks/ContextHooks/useMateriaContext"

export default function Carreras() {
  const { agregarMateriaSeleccionada } = useMateriaContext()
  return (
    <div className="min-h-screen p-6 flex flex-col">
      <h1 className="text-2xl font-bold mb-6 text-white text-center">
        Gestión de Carreras
      </h1>

      <div className="flex flex-col gap-6 max-w-7xl mx-auto w-full">
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6 h-fit">
          <div className="h-full">
            <CarreraForm />
          </div>
          <div className="h-full overflow-y-auto">
            <MateriasSelector onClick={agregarMateriaSeleccionada} />
          </div>
        </div>

        <div className="bg-white rounded-xl p-6 shadow-lg">
          <MateriasSeleccionadas />
        </div>
      </div>
    </div>
  )
}
