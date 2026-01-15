import { useState } from "react"
import {
  useBuscarMateriaPorNombre,
  useTraerMateria,
} from "../../hooks/useMateria"
import NodoDeMaterias from "../NodoDeMaterias/NodoDeMaterias"
import Modal from "../Ui/Modal"
import DetalleMateria from "../Materia/DetalleMateria"
import Dropdown from "../Ui/Dropdown"

export default function MostrarMateria() {
  const { buscarMateriaPorNombre, loading, materias } =
    useBuscarMateriaPorNombre()
  const { traerMateria, materia } = useTraerMateria()

  const [materiaNombre, setMateriaNombre] = useState("")
  const [isOpenModal, setIsOpenModal] = useState(false)

  const handleSubmit = async (e) => {
    e.preventDefault()
    if (materiaNombre) {
      buscarMateriaPorNombre(materiaNombre)
    }
  }

  console.log({ materia })
  return (
    <div className="flex flex-col items-center justify-center pt-10">
      <div className="flex w-72 flex-row gap-3.5 rounded bg-white p-6 shadow-sm h-44">
        <form onSubmit={handleSubmit} className="flex-1 flex flex-col gap-3.5">
          <input
            className="border border-gray-300 rounded p-2"
            onChange={(e) => {
              setMateriaNombre(e.target.value)
              console.log(materiaNombre)
            }}
            value={materiaNombre}
            placeholder="Ingresa nombre materia..."
            disabled={loading}
          />
          <button
            className="h-10 bg-red-950 text-white py-2 px-4 rounded disabled:opacity-50 flex-shrink-0"
            type="submit"
            disabled={loading}
          >
            {loading ? "Buscando..." : "Buscar Materia"}
          </button>
        </form>
        <div className="flex relative items-center m-7">
          {materias.length < 1 ? (
            ""
          ) : (
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
