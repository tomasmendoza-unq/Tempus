import { useState } from "react"
import { useTraerMateria } from "../../hooks/useMateria"

export default function MostrarMateria() {
  const { traerMateria, loading, materia } = useTraerMateria()
  const [idBusqueda, setIdBusqueda] = useState("")

  const handleSubmit = async (e) => {
    e.preventDefault()
    if (idBusqueda) {
      try {
        await traerMateria(idBusqueda)
      } catch (err) {
        console.error("Error al buscar materia:", err)
      }
    }
  }

  return (
    <div className="flex justify-center pt-10">
      <div className="flex w-72 flex-col items-center gap-3.5 rounded bg-white p-6 shadow-sm">
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
            disabled={loading}
          >
            {loading ? "Buscando..." : "Buscar Materia"}
          </button>
        </form>
        {materia && (
          <div className="mt-2 w-full rounded bg-gray-100 p-4">
            <h3 className="font-bold">{materia.materiaNombre}</h3>
            <p className="text-sm">ID: {materia.materiaId}</p>
          </div>
        )}
      </div>
    </div>
  )
}
