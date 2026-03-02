import { useEffect, useState } from "react"
import { useGenerarHorario } from "../hooks/useHorario"
import { useTraerTodasMaterias } from "../hooks/useMateria" 
import { HorarioGrid } from "../components/Horario/HorarioGrid"

export default function GeneradorHorarios() {
  const { generarHorarios, cargando, resultados } = useGenerarHorario()
  const { traerMaterias, materias, cargando: cargandoMaterias } = useTraerTodasMaterias()
  
  const [selectedIds, setSelectedIds] = useState([])
  const [cantidad, setCantidad] = useState(3)

  // Cargar las materias al montar el componente
  useEffect(() => {
    traerMaterias()
  }, [])

  const toggleMateria = (id) => {
    setSelectedIds(prev => 
      prev.includes(id) 
        ? prev.filter(mId => mId !== id) 
        : [...prev, id]
    )
  }

  const handleCalcular = () => {
    if (selectedIds.length === 0) {
      return alert("Seleccioná al menos una materia")
    }
    generarHorarios(selectedIds, cantidad)
  }

  return (
    <div className="flex flex-col p-6 space-y-6">
      <h2 className="text-2xl font-bold text-gray-800 text-center">Configurar Horario</h2>
      
      {/* Sección de Selección de Materias */}
      <div className="bg-white p-6 rounded-lg shadow border border-gray-200">
        <h3 className="text-lg font-semibold mb-4 text-gray-700">Selecciona tus materias</h3>
        
        {cargandoMaterias ? (
          <p className="text-gray-500 animate-pulse">Cargando lista de materias...</p>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-3">
            {materias?.map((materia) => (
              <button
                key={materia.materiaId}
                onClick={() => toggleMateria(materia.materiaId)}
                className={`p-3 text-left rounded-lg border transition-all ${
                  selectedIds.includes(materia.materiaId)
                    ? "bg-red-50 border-red-950 text-red-950 font-bold ring-1 ring-red-950"
                    : "bg-gray-50 border-gray-200 text-gray-600 hover:bg-gray-100"
                }`}
              >
                {materia.materiaNombre}
              </button>
            ))}
          </div>
        )}
      </div>

      {/* Panel de Control (Cantidad y Botón) */}
      <div className="bg-white p-4 rounded-lg shadow border border-gray-200 flex flex-wrap gap-4 items-end justify-center">
        <div>
          <label className="block text-sm font-medium text-gray-700">Cantidad de opciones</label>
          <input 
            type="number" 
            min="1"
            max="10"
            className="mt-1 block w-24 border-gray-300 rounded-md shadow-sm border p-2"
            value={cantidad}
            onChange={(e) => setCantidad(Number(e.target.value))}
          />
        </div>
        
        <button
          onClick={handleCalcular}
          disabled={cargando || selectedIds.length === 0}
          className="bg-red-950 text-white px-8 py-2 rounded-md hover:bg-red-900 transition-all disabled:bg-gray-400 disabled:cursor-not-allowed shadow-md"
        >
          {cargando ? "Calculando..." : "Generar Combinaciones"}
        </button>
      </div>

      {/* Resultados en Grilla */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
        {resultados?.length > 0 ? (
          resultados.map((horario, index) => (
            <div key={index} className="space-y-3 animate-fadeIn">
              <div className="flex items-center gap-2">
                <span className="flex items-center justify-center bg-red-950 text-white w-8 h-8 rounded-full text-sm font-bold">
                  {index + 1}
                </span>
                <h4 className="font-semibold text-gray-700">Opción de Horario</h4>
              </div>
              <HorarioGrid horario={horario} />
            </div>
          ))
        ) : !cargando && (
          <div className="col-span-full text-center py-10 text-gray-400 italic">
            No hay combinaciones para mostrar. Seleccioná materias y hacé clic en Generar.
          </div>
        )}
      </div>
    </div>
  )
}