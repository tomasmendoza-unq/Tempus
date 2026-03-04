export default function TarjetaMateria({ materia, children }) {
  return (
    <div className="relative bg-white shadow rounded-lg p-5 min-w-50 max-w-50 border-2 border-gray-200 truncate">
      <h2 className="text-xl font-bold text-gray-800">
        {materia.materiaNombre}
      </h2>
      {children}
    </div>
  )
}
