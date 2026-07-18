import { useMateriaContext } from "../../hooks/ContextHooks/useMateriaContext"
import TarjetaMateria from "./TarjetaMateria"
import { Trash2 } from "feather-icons-react"

export default function MateriasSeleccionadas() {
  const { materiasSeleccionadas, eliminarMateriaSeleccionada } =
    useMateriaContext()

  return (
    <div className="w-full">
      <h2 className="text-xl font-bold text-gray-800 mb-4">
        Materias Seleccionadas
      </h2>

      {materiasSeleccionadas.length === 0 ? (
        <p className="text-gray-500 text-center py-8">
          No hay materias seleccionadas
        </p>
      ) : (
        <ul className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4 max-h-64 overflow-y-auto pr-2">
          {materiasSeleccionadas.map((m) => (
            <li key={m.materiaId}>
              <TarjetaMateria materia={m}>
                <button
                  onClick={() => eliminarMateriaSeleccionada(m)}
                  className="mt-2 text-red-500 hover:text-red-700 flex items-center gap-1"
                >
                  <Trash2 size={16} />
                  <span>Eliminar</span>
                </button>
              </TarjetaMateria>
            </li>
          ))}
        </ul>
      )}
    </div>
  )
}
