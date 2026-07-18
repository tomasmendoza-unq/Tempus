import { useEffect, useState } from "react"
import { useTraerTodasMaterias, useTraerMateria } from "../../hooks/useMateria"
import TarjetaMateria from "./TarjetaMateria"
import { Plus, List } from "feather-icons-react"
import BuscadorDeMaterias from "./BuscadorDeMaterias"
import Modal from "../Ui/Modal/Modal"
import DetalleMateria from "./DetalleMateria"

export default function MateriasSelector({
  onClick,
  buttonChildren,
  buttonStyles,
  buscadorClassName,
  selectedId,
}) {
  const { traerMaterias, materias, cargando } = useTraerTodasMaterias()
  const { traerMateria, materia } = useTraerMateria()
  const [isModalOpen, setIsModalOpen] = useState(false)

  useEffect(() => { traerMaterias() }, [])

  if (cargando) return <p className="text-white text-center py-10">Cargando catálogo...</p>

  return (
    <div className="flex flex-col w-full relative">

      <div className={`sticky top-0 z-30 bg-[#1e1e1e] pt-2 pb-6 ${buscadorClassName}`}>
        <BuscadorDeMaterias />
      </div>

      <ul className="grid grid-cols-1 sm:grid-cols-2 xl:grid-cols-3 gap-6 w-full">
        {materias?.map((m) => {
          const isSelected = selectedId === m.materiaId
          const isDimmed = selectedId != null && !isSelected
          
          return (
            <li key={m.materiaId} className={`transition-all duration-300 ${isDimmed ? "opacity-40 scale-95" : "opacity-100"}`}>
              <TarjetaMateria materia={m}>
                <button
                  onClick={() => onClick(m)}
                  className={buttonStyles || "text-blue-500 hover:text-blue-700 flex items-center gap-2 text-sm font-semibold"}
                >
                  {buttonChildren || <><Plus size={16} /> Agregar</>}
                </button>
                <button
                  className="text-green-500 hover:text-green-700 flex items-center gap-2 text-sm font-semibold"
                  onClick={async () => {
                    await traerMateria(m.materiaId)
                    setIsModalOpen(true)
                  }}
                >
                  <List size={16} /> Ver detalles
                </button>
              </TarjetaMateria>
            </li>
          )
        })}
      </ul>

      <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)}>
        <DetalleMateria materia={materia} />
      </Modal>
    </div>
  )
}