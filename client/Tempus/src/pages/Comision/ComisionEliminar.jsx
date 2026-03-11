import { useParams, useNavigate } from "react-router-dom"

export default function ComisionEliminar() {
  const { id } = useParams()
  const navigate = useNavigate()

  const handleEliminar = async () => {
    // TODO: llamar al service para eliminar la comisión
    navigate("/comisiones/mostrar")
  }

  return (
    <div className="max-w-md mx-auto mt-10 p-6 bg-white rounded shadow">
      <h1 className="text-2xl font-bold mb-4 text-red-700">
        Eliminar Comisión
      </h1>
      <p className="mb-6">
        ¿Estás seguro de que querés eliminar la comisión <strong>{id}</strong>?
      </p>
      <div className="flex gap-4">
        <button
          onClick={handleEliminar}
          className="bg-red-600 hover:bg-red-800 text-white font-bold py-2 px-4 rounded"
        >
          Confirmar
        </button>
        <button
          onClick={() => navigate("/comisiones/mostrar")}
          className="bg-gray-400 hover:bg-gray-500 text-white font-bold py-2 px-4 rounded"
        >
          Cancelar
        </button>
      </div>
    </div>
  )
}
