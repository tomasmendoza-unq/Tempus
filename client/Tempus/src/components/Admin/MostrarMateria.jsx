import { useState } from "react"
import { useTraerMateria } from "../../hooks/useMateria"
import NodoDeMaterias from "../NodoDeMaterias/NodoDeMaterias"
import Modal from "../Ui/Modal/Modal"
import DetalleMateria from "../Materia/DetalleMateria"
import Dropdown from "../Ui/Dropdown/Dropdown"
import BuscadorDeMaterias from "./BuscadorDeMaterias"

export default function MostrarMateria() {
  const { traerMateria, materia, materias } = useTraerMateria()

  const [isOpenModal, setIsOpenModal] = useState(false)

  return (
    <div className="flex flex-col items-center justify-center pt-10">
      <div className="flex w-72 flex-row gap-3.5 rounded bg-white p-6 shadow-sm h-44">
        <BuscadorDeMaterias />
        <div className="flex relative items-center m-7">
          {materias?.length > 0 && (
            <Dropdown
              tag1={"materiaNombre"}
              tag2={"materiaId"}
              elements={materias}
              callback={(elem) => {
                traerMateria(elem[1])
              }}
            />
          )}
        </div>
      </div>

      {materia && (
        <>
          <div className="flex items-center justify-center mt-2 w-full p-4">
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
