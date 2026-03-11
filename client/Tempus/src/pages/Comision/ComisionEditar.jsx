import { useParams, useNavigate } from "react-router-dom"

export default function ComisionEditar() {
  const { id } = useParams()
  const navigate = useNavigate()

  const handleGuardar = async () => {
    // TODO: llamar al service para editar la comisión
    navigate("/comisiones/mostrar")
  }

  return (
    <div className="max-w-md mx-auto mt-10 p-6 bg-white rounded shadow">
      <h1 className="text-2xl font-bold mb-4">Editar Comisión</h1>
      <p className="mb-4">
        Editando comisión: <strong>{id}</strong>
      </p>

      <div className="flex gap-4">
        <button
          onClick={handleGuardar}
          className="bg-green-600 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
        >
          Guardar
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
