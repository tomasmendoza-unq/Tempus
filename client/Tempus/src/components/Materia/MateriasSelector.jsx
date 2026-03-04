import { useEffect } from "react"
import { useTraerTodasMaterias } from "../../hooks/useMateria"
import TarjetaMateria from "./TarjetaMateria"
import { Plus } from "feather-icons-react"
import { useMateriaContext } from "../../hooks/ContextHooks/useMateriaContext"
import BuscadorDeMaterias from "./BuscadorDeMaterias"

export default function MateriasSelector() {
  const { traerMaterias, materias, cargando } = useTraerTodasMaterias()
  const { agregarMateriaSeleccionada } = useMateriaContext()

  useEffect(() => {
    traerMaterias()
  }, [])

  if (cargando) return <p>Cargando materias...</p>

  return (
    <div className="flex flex-col ">
      <BuscadorDeMaterias />
      <ul className="flex flex-wrap gap-5">
        {materias?.map((m) => (
          <li key={m.materiaId}>
            <TarjetaMateria materia={m}>
              <button
                onClick={() => agregarMateriaSeleccionada(m)}
                className="mt-2 text-blue-500 hover:text-blue-700 flex items-center gap-1"
              >
                <Plus size={16} />
                <span>Agregar</span>
              </button>
            </TarjetaMateria>
          </li>
        ))}
      </ul>
    </div>
  )
}
