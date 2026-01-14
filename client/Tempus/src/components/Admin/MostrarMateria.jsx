import { useState } from "react"
import { useTraerMateria } from "../../hooks/materia"

export default function MostrarMateria() {
  const { traerMateria, loading, error, materia } = useTraerMateria()
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
    <div className="flex bg-white min-h-25 min-w-25 items-center justify-center flex-col gap-3.5 p-6 rounded">
      <form onSubmit={handleSubmit} className="w-full flex flex-col gap-3.5">
        <input
          onChange={(e) => setIdBusqueda(e.target.value)}
          value={idBusqueda}
          placeholder="Ingresa ID materia..."
          disabled={loading}
        />
        {error && <p className="text-red-500 text-sm">{error.message}</p>}
        <button
          className="mt-4 bg-red-950 text-white py-2 px-4 rounded disabled:opacity-50"
          type="submit"
          disabled={loading}
        >
          {loading ? "Buscando..." : "Buscar Materia"}
        </button>
      </form>
      {materia && (
        <div className="mt-4 p-4 bg-gray-100 rounded w-full">
          <h3 className="font-bold">{materia.materiaNombre}</h3>
          <p className="text-sm">ID: {materia.materiaId}</p>
        </div>
      )}
    </div>
  )
}
