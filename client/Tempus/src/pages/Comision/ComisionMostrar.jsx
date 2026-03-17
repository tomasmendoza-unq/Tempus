import ComisionCard from "../../components/Comision/ComisionCard"
import { useComision } from "../../hooks/useComision"
import { useEffect, useState } from "react"
import { Trash2, Edit } from "feather-icons-react"
import { useNavigate } from "react-router-dom"
import Modal from "../../components/Ui/Modal/Modal"

export default function ComisionMostrar() {
  const [data, setData] = useState({ content: [], totalPages: 0, number: 0 })
  const [isOpenModal, setIsOpenModal] = useState(false)
  const [comisionSeleccionada, setComisionSeleccionada] = useState(null)
  const navigate = useNavigate()
  const { obtenerTodasComisiones, eliminarComision } = useComision()
  const fetchComisiones = async () => {
    try {
      const data = await obtenerTodasComisiones()
      setData(data)
    } catch (error) {
      console.error("Error al obtener las comisiones:", error)
    }
  }

  useEffect(() => {
    fetchComisiones()
  }, [eliminarComision])

  console.log("Comisiones obtenidas:", { data })
  return (
    <div className="flex flex-col justify-center items-center gap-5">
      <h1 className="text-2xl text-white font-bold mb-4">Comisiones</h1>
      <div className="flex flex-wrap gap-4 justify-center">
        {data?.content?.map((comision) => (
          <ComisionCard key={comision.comisionId} comision={comision}>
            <div>
              <button
                onClick={() => {
                  setComisionSeleccionada(comision.comisionId)
                  setIsOpenModal(true)
                }}
                className="mt-4 text-red-600 hover:bg-red-900 transition-colors duration-200 hover:text-white font-bold py-2 px-4 rounded"
              >
                <Trash2 />
              </button>
              <button
                onClick={() =>
                  navigate(`/comisiones/editar/${comision?.comisionId}`)
                }
                className="mt-4 text-green-600 hover:bg-green-900 transition-colors duration-200 hover:text-white font-bold py-2 px-4 rounded"
              >
                <Edit />
              </button>
            </div>
          </ComisionCard>
        ))}
      </div>

      <Modal isOpen={isOpenModal} onClose={() => setIsOpenModal(false)}>
        <div className="flex flex-col items-center justify-center gap-4">
          <h2 className="text-2xl font-bold text-gray-900">
            ¿Estás seguro de que querés eliminar la comisión #
            {comisionSeleccionada}?
          </h2>
          <p className="text-gray-600">Esta acción no se puede deshacer.</p>
          <div className="flex gap-3 justify-end mt-4">
            <button
              onClick={() => setIsOpenModal(false)}
              className="px-6 py-2.5 border border-gray-300 rounded-lg text-gray-700 font-semibold hover:bg-gray-50 transition-colors"
            >
              Cancelar
            </button>
            <button
              onClick={() => {
                eliminarComision(comisionSeleccionada)
                setIsOpenModal(false)
                setComisionSeleccionada(null)
              }}
              className="px-6 py-2.5 bg-red-950 hover:bg-red-900 text-white font-semibold rounded-lg transition-colors"
            >
              Eliminar
            </button>
          </div>
        </div>
      </Modal>

      <div className="mt-2 flex items-center gap-3 rounded-xl bg-white/10 px-4 py-2">
        <button
          className="rounded-lg bg-red-600 px-4 py-2 text-sm font-semibold text-white transition-colors hover:bg-red-700 disabled:cursor-not-allowed disabled:bg-gray-600 disabled:text-gray-300"
          onClick={() => fetchComisiones(data.number - 1)}
          disabled={data.first}
        >
          Anterior
        </button>

        <span className="text-sm font-medium text-white">
          Pagina {data.number + 1} de {data.totalPages}
        </span>

        <button
          className="rounded-lg bg-red-600 px-4 py-2 text-sm font-semibold text-white transition-colors hover:bg-red-700 disabled:cursor-not-allowed disabled:bg-gray-600 disabled:text-gray-300"
          onClick={() => fetchComisiones(data.number + 1)}
          disabled={data.last}
        >
          Siguiente
        </button>
      </div>
    </div>
  )
}
