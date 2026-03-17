export default function TarjetaMateria({ materia, children }) {
  return (

    <div className="relative bg-white shadow rounded-lg p-5 w-full h-full border-2 border-gray-200 flex flex-col justify-between">
      <div className="flex flex-col gap-1">
        <h2 className="text-lg font-bold text-gray-800 leading-tight whitespace-normal break-words">
          {materia.materiaNombre}
        </h2>

        <span className="text-xs text-gray-400 font-mono">#{materia.materiaId}</span>
      </div>
      
      <div className="mt-4">
        {children}
      </div>
    </div>
  )
}