import { Trash2, Edit } from "feather-icons-react"
import { useNavigate } from "react-router-dom"

export default function ComisionCard({ comision }) {
  const navigate = useNavigate()

  return (
    <div className="w-full max-w-xs flex flex-col justify-center items-center border-2 border-gray-950 bg-white py-5 text-black hover:shadow-md hover:shadow-red-700 hover:bg-red-300 duration-200 rounded rounded-2xl">
      <h2 className="text-xl font-bold mb-2 text-red-500">
        Comision: #{comision?.comisionId || "Sin nombre"}
      </h2>
      <p className="text-lg mb-2">
        {comision?.materia?.materiaNombre || "Sin nombre"}
      </p>
      {comision?.claseHorario?.length > 0 ? (
        comision.claseHorario.map((h, i) => (
          <p key={i} className="text-md">
            {h.dia} · {h.inicio} - {h.fin}
          </p>
        ))
      ) : (
        <p className="text-md">Sin horarios</p>
      )}
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
