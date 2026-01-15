import { useState } from "react"
import { useTraerMateria } from "../../hooks/useMateria"
import NodoDeMaterias from "../NodoDeMaterias/NodoDeMaterias"
import Modal from "../Ui/Modal"
import DetalleMateria from "../Materia/DetalleMateria"

export default function MostrarMateria() {
  const { traerMateria, loading, materia } = useTraerMateria()
  const [idBusqueda, setIdBusqueda] = useState("")
  const [isOpenModal, setIsOpenModal] = useState(false)

  const handleSubmit = async (e) => {
    e.preventDefault()
    if (idBusqueda) {
      try {
        console.log(await traerMateria(idBusqueda))
      } catch (err) {
        console.error("Error al buscar materia:", err)
      }
    }
  }

  return (
    <div className="flex flex-col items-center justify-center pt-10">
      <div className="flex w-72 flex-col gap-3.5 rounded bg-white p-6 shadow-sm">
        <form onSubmit={handleSubmit} className="w-full flex flex-col gap-3.5">
          <input
            className="border border-gray-300 rounded p-2"
            onChange={(e) => setIdBusqueda(e.target.value)}
            value={idBusqueda}
            placeholder="Ingresa ID materia..."
            disabled={loading}
          />
          <button
            className="mt-2 bg-red-950 text-white py-2 px-4 rounded disabled:opacity-50"
            type="submit"
            disabled={loading || !idBusqueda.trim()}
          >
            {loading ? "Buscando..." : "Buscar Materia"}
          </button>
        </form>
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
