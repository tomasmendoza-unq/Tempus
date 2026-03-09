import { useEffect, useState } from "react"
import {
  useBuscarMateriaPorNombre,
  useTraerTodasMaterias,
} from "../../hooks/useMateria"
import CorrelativasDisplayMaterias from "./CorrelativasDisplayMaterias"
import { Search, RefreshCw } from "feather-icons-react"

export default function CorrelativasSelector({
  materia,
  setMateria,
  titulo,
  excludedId,
  multiple = false,
}) {
  const { buscarMateriaPorNombre } = useBuscarMateriaPorNombre()
  const { traerMaterias } = useTraerTodasMaterias()
  const [materias, setMaterias] = useState([])
  const [query, setQuery] = useState("")

  useEffect(() => {
    const settearMaterias = async () => {
      const data = await traerMaterias()
      setMaterias(data)
    }
    settearMaterias()
  }, [])

  const handleBuscar = async () => {
    if (!query.trim()) return
    const data = await buscarMateriaPorNombre(query)
    setMaterias(data)
  }

  const handleRefresh = async () => {
    setQuery("")
    const data = await traerMaterias()
    setMaterias(data)
  }

  return (
    <div className="flex flex-col border border-red-950 rounded">
      <div className="bg-red-950 w-full px-20 py-3 rounded-t">
        <h2 className="text-white">{titulo}</h2>
      </div>
      <div className="px-3 py-2">
        <div className="relative">
          <button
            type="button"
            onClick={handleBuscar}
            className="absolute left-2.5 top-1/2 -translate-y-1/2 text-gray-400 hover:text-red-950 transition"
          >
            <Search size={14} />
          </button>
          <input
            className="w-full border border-gray-200 rounded-lg pl-8 pr-8 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-red-900 focus:border-transparent transition"
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            onKeyDown={(e) => e.key === "Enter" && handleBuscar()}
            placeholder="Buscar materias..."
          />
          <button
            type="button"
            onClick={handleRefresh}
            className="absolute right-2.5 top-1/2 -translate-y-1/2 text-gray-400 hover:text-red-950 transition"
            title="Ver todas las materias"
          >
            <RefreshCw size={14} />
          </button>
        </div>
      </div>
      <CorrelativasDisplayMaterias
        onClick={(materiaSeleccionada) => {
          if (multiple) {
            setMateria((prev) => {
              const exists = prev.some(
                (m) => m.materiaId === materiaSeleccionada.materiaId
              )
              return exists
                ? prev.filter(
                    (m) => m.materiaId !== materiaSeleccionada.materiaId
                  )
                : [...prev, materiaSeleccionada]
            })
          } else {
            setMateria(materiaSeleccionada)
          }
        }}
        materias={materias}
        selectedId={!multiple ? materia?.materiaId : undefined}
        selectedIds={multiple ? materia.map((m) => m.materiaId) : undefined}
        excludedId={excludedId}
      />
    </div>
  )
}
