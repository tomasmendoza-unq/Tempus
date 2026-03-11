import { Trash2, Edit } from "feather-icons-react"
import { useNavigate } from "react-router-dom"

export default function ComisionCard({ comision }) {
  const navigate = useNavigate()

  return (
    <div className="flex flex-col border border-gray-500 bg-white p-5 text-black hover:shadow-md hover:shadow-red-700 hover:bg-red-300 duration-200 rounded">
      <h2 className="text-xl font-bold mb-2">
        {comision?.comisionId || "Sin nombre"}
      </h2>
      <p className="text-lg mb-2">
        {comision?.materia?.materiaNombre || "Sin nombre"}
      </p>
      <p className="text-md">{comision?.claseHorario || "Sin nombre"}</p>
      <div>
        <button
          onClick={() =>
            navigate(`/comisiones/eliminar/${comision?.comisionId}`)
          }
          className="mt-4 text-red-600 hover:bg-red-900 transition-colors duration-200 hover:text-white font-bold py-2 px-4 rounded"
        >
          <Trash2 />
        </button>
        <button
          onClick={() => navigate(`/comisiones/editar/${comision?.comisionId}`)}
          className="mt-4 text-green-600 hover:bg-green-900 transition-colors duration-200 hover:text-white font-bold py-2 px-4 rounded"
        >
          <Edit />
        </button>
      </div>
    </div>
  )
}
