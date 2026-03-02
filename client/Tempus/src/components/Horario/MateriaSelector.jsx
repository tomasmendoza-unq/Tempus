export function MateriaSelector({ materias, selectedIds, onToggle, cargando }) {
  if (cargando) return <p className="text-gray-500 animate-pulse">Cargando lista de materias...</p>;

  return (
    <div className="bg-white p-6 rounded-lg shadow border border-gray-200">
      <h3 className="text-lg font-semibold mb-4 text-gray-700">Selecciona tus materias</h3>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-3">
        {materias?.map((materia) => (
          <button
            key={materia.materiaId}
            onClick={() => onToggle(materia.materiaId)}
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
    </div>
  );
}