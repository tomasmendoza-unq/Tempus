export default function Materia({ materia }) {
  return (
    <div className="flex flex-col gap-6 p-2">
      <div className="border-b border-gray-200 pb-4">
        <h2 className="text-2xl font-bold text-gray-900">
          {materia.materiaNombre}
        </h2>
        <span className="text-sm text-gray-400">ID: {materia.materiaId}</span>
      </div>
      <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
        <div className="bg-gray-50 rounded-xl p-4">
          <p className="text-xs font-semibold uppercase tracking-wide text-gray-400 mb-1">
            Carrera
          </p>
          <p className="text-gray-800 font-medium">{materia.carrera || "—"}</p>
        </div>
        <div className="bg-gray-50 rounded-xl p-4">
          <p className="text-xs font-semibold uppercase tracking-wide text-gray-400 mb-2">
            Correlativas
          </p>
          {materia.correlativas && materia.correlativas.length > 0 ? (
            <ul className="list-disc list-inside space-y-1">
              {materia.correlativas.map((correlativa) => (
                <li
                  key={correlativa.materiaId}
                  className="text-gray-800 text-sm"
                >
                  {correlativa.materiaNombre}
                </li>
              ))}
            </ul>
          ) : (
            <p className="text-gray-500 text-sm">No tiene correlativas.</p>
          )}
        </div>
        <div className="bg-gray-50 rounded-xl p-4 sm:col-span-2">
          <p className="text-xs font-semibold uppercase tracking-wide text-gray-400 mb-1">
            Comisiones
          </p>
          <p className="text-gray-800 text-sm">
            {materia.comisiones && materia.comisiones.length > 0
              ? materia.comisiones.map((c) => c.comisionId).join(", ")
              : "No tiene comisiones disponibles."}
          </p>
        </div>
      </div>
    </div>
  )
}
