import { useState } from "react"
import { Search } from "feather-icons-react"
import {
  useBuscarMateriaPorNombre,
  useTraerTodasMaterias,
} from "../../hooks/useMateria"

export default function BuscadorDeMaterias() {
  const { buscarMateriaPorNombre } = useBuscarMateriaPorNombre()
  const { traerMaterias } = useTraerTodasMaterias()
  const [busqueda, setBusqueda] = useState("")

  const handleBuscar = () => {
    if (busqueda.trim()) {
      buscarMateriaPorNombre(busqueda)
    }
  }

  const handleVerTodas = () => {
    setBusqueda("")
    traerMaterias()
  }

  const handleKeyDown = (e) => {
    if (e.key === "Enter") {
      handleBuscar()
    }
  }

  return (
    <div className="mb-5 flex flex-col gap-2">
      <div className="relative">
        <button
          onClick={(e) => {
            e.preventDefault()
            handleBuscar()
          }}
          className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400 hover:text-blue-500 transition cursor-pointer"
        >
          <Search size={18} />
        </button>
        <input
          type="text"
          value={busqueda}
          onChange={(e) => setBusqueda(e.target.value)}
          onKeyDown={handleKeyDown}
          placeholder="Buscar materia..."
          className="w-full pl-10 pr-4 py-2 rounded-lg border border-gray-200 bg-white text-gray-800 placeholder-gray-400 shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-400 focus:border-transparent transition"
        />
      </div>
      <button
        onClick={handleVerTodas}
        className="w-full py-2 rounded-lg bg-red-950 text-white font-medium hover:bg-red-900 transition cursor-pointer"
      >
        Ver todas las materias
      </button>
    </div>
  )
}
