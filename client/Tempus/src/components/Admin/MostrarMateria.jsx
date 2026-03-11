import { useState } from "react"
import { useTraerMateria } from "../../hooks/useMateria"
import NodoDeMaterias from "../NodoDeMaterias/NodoDeMaterias"
import Modal from "../Ui/Modal/Modal"
import DetalleMateria from "../Materia/DetalleMateria"
import Dropdown from "../Ui/Dropdown/Dropdown"
import BuscadorDeMaterias from "./BuscadorDeMaterias"
import Titulo from "../Ui/Titulo/Titulo"

export default function MostrarMateria() {
  const { traerMateria, materia, materias } = useTraerMateria()

  const [isOpenModal, setIsOpenModal] = useState(false)

  return (
    <div className="flex flex-col items-center w-full">
      <Titulo texto="Buscar materias" />
      <div className="bg-white rounded-2xl shadow-md border border-gray-100 p-8 w-full max-w-md flex flex-col gap-4">
        <BuscadorDeMaterias />
        {materias?.length > 0 && (
          <div className="border-t border-gray-100 pt-4">
            <label className="text-sm font-medium text-gray-700 block mb-2">
              Resultados
            </label>
            <Dropdown
              customClass={
                "border border-0.5 bg-red-950 text-white rounded hover:shadow-md hover:shadow-red-700 hover:bg-red-900 transition-all duration-200"
              }
              tag1={"materiaNombre"}
              tag2={"materiaId"}
              elements={materias}
              callback={async (elem) => {
                await traerMateria(elem[1])
              }}
            />
          </div>
        )}
      </div>

      {materia && (
        <>
          <div className="flex items-center justify-center mt-6 w-full">
            <NodoDeMaterias
              materia={materia}
              onClick={() => setIsOpenModal(true)}
            />
          </div>
          <Modal isOpen={isOpenModal} onClose={() => setIsOpenModal(false)}>
            <DetalleMateria materia={materia} />
          </Modal>
        </>
      )}
    </div>
  )
}
