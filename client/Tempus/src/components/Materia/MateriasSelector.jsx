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

  useEffect(() => {
    traerMaterias()
  }, [])

  if (cargando) return (
    <div className="flex justify-center py-20">
      <p className="text-gray-400 animate-pulse">Cargando catálogo de materias...</p>
    </div>
  )

  return (
    <div className="flex flex-col w-full">
      <div className={`${buscadorClassName} sticky top-0 z-20 bg-[#1e1e1e] pb-6`}>
        <BuscadorDeMaterias />
      </div>
      <ul className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-2 xl:grid-cols-3 gap-6 w-full">
        {materias?.map((m) => {
          const isSelected = selectedId != null && selectedId === m.materiaId
          const isDimmed = selectedId != null && selectedId !== m.materiaId
          
          return (
            <li
              key={m.materiaId}
              className={`transition-all duration-300 w-full flex ${
                isDimmed ? "opacity-40 scale-[0.98]" : "opacity-100 scale-100"
              } ${isSelected ? "ring-2 ring-red-500 rounded-xl" : ""}`}
            >
              <TarjetaMateria materia={m}>
                <div className="flex flex-col gap-2 mt-3">
                  <button
                    onClick={() => onClick(m)}
                    className={buttonStyles || "text-blue-500 hover:text-blue-400 flex items-center gap-2 text-sm font-medium transition-colors"}
                  >
                    {buttonChildren || (
                      <>
                        <Plus size={16} />
                        <span>Agregar materia</span>
                      </>
                    )}
                  </button>
                  
                  <button
                    className="text-green-500 hover:text-green-400 flex items-center gap-2 text-sm font-medium transition-colors"
                    onClick={async () => {
                      await traerMateria(m.materiaId)
                      setIsModalOpen(true)
                    }}
                  >
                    <List size={16} />
                    <span>Ver detalles</span>
                  </button>
                </div>
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