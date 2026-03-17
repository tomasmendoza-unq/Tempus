import ComisionCard from "../../components/Comision/ComisionCard"
import { useComision } from "../../hooks/useComision"
import { useEffect, useState } from "react"

export default function ComisionMostrar() {
  const [data, setData] = useState({ content: [], totalPages: 0, number: 0 })

  const { obtenerTodasComisiones } = useComision()
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
  }, [])

  console.log("Comisiones obtenidas:", { data })
  return (
    <div className="flex flex-col justify-center items-center gap-5">
      <h1 className="text-2xl text-white font-bold mb-4">Comisiones</h1>
      <div className="flex flex-wrap gap-4 justify-center">
        {data?.content?.map((comision) => (
          <ComisionCard key={comision.comisionId} comision={comision} />
        ))}
      </div>
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
