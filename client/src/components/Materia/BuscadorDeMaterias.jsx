import { useState } from "react"
import { Search } from "feather-icons-react"
import { useBuscarMateriaPorNombre, useTraerTodasMaterias } from "../../hooks/useMateria"

export default function BuscadorDeMaterias() {
  const { buscarMateriaPorNombre } = useBuscarMateriaPorNombre()
  const { traerMaterias } = useTraerTodasMaterias()
  const [busqueda, setBusqueda] = useState("")

  const handleBuscar = () => {
    if (busqueda.trim()) buscarMateriaPorNombre(busqueda)
  }

  const handleVerTodas = () => {
    setBusqueda("")
    traerMaterias()
  }

  return (
    <div className="flex flex-col gap-3 w-full">
      <div className="relative w-full">
        <button
          onClick={(e) => { e.preventDefault(); handleBuscar(); }}
          className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400 hover:text-blue-500 transition cursor-pointer z-10"
        >
          <Search size={18} />
        </button>
        <input
          type="text"
          value={busqueda}
          onChange={(e) => setBusqueda(e.target.value)}
          onKeyDown={(e) => e.key === "Enter" && handleBuscar()}
          placeholder="Buscar materia..."
          className="w-full pl-10 pr-4 py-2.5 rounded-lg border border-gray-200 bg-white text-gray-800 shadow-sm focus:ring-2 focus:ring-blue-400 outline-none transition"
        />
      </div>
      <button
        onClick={handleVerTodas}
        className="w-full py-2.5 rounded-lg bg-red-950 text-white font-bold hover:bg-red-900 transition cursor-pointer shadow-md"
      >
        Ver todas las materias
      </button>
    </div>
  )
}