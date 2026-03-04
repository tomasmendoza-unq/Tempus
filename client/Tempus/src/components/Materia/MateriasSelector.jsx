import { useEffect } from "react"
import { useTraerTodasMaterias } from "../../hooks/useMateria"
import TarjetaMateria from "./TarjetaMateria"
import { Plus } from "feather-icons-react"
import BuscadorDeMaterias from "./BuscadorDeMaterias"

export default function MateriasSelector({
  onClick,
  buttonChildren,
  buttonStyles,
  buscadorClassName,
  selectedId,
}) {
  const { traerMaterias, materias, cargando } = useTraerTodasMaterias()

  useEffect(() => {
    traerMaterias()
  }, [])

  if (cargando) return <p>Cargando materias...</p>

  return (
    <div className="flex flex-col ">
      <div className={buscadorClassName}>
        <BuscadorDeMaterias />
      </div>
      <ul className="flex flex-wrap gap-5">
        {materias?.map((m) => {
          const isSelected = selectedId != null && selectedId === m.materiaId
          const isDimmed = selectedId != null && selectedId !== m.materiaId
          return (
            <li
              key={m.materiaId}
              className={`transition-opacity duration-300 ${isDimmed ? "opacity-40" : "opacity-100"} ${isSelected ? "ring-2 ring-red-500 rounded-lg" : ""}`}
            >
              <TarjetaMateria materia={m}>
                <button
                  onClick={() => onClick(m)}
                  className={`mt-2 ${buttonStyles || "text-blue-500 hover:text-blue-700 flex items-center gap-1"}`}
                >
                  {buttonChildren || (
                    <>
                      <Plus size={16} />
                      <span>Agregar</span>
                    </>
                  )}
                </button>
              </TarjetaMateria>
            </li>
          )
        })}
      </ul>
    </div>
  )
}
