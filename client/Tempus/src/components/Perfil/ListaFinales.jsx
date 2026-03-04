import { Trash2 } from "feather-icons-react"; 

export function ListaFinales({ materias, onDesaprobar }) {
  return (
    <section className="space-y-4">
      <h3 className="text-lg font-bold text-gray-800 italic border-l-4 border-red-950 pl-3">
        Materias Aprobadas
      </h3>
      <div className="bg-white rounded-xl border border-gray-100 shadow-sm overflow-hidden">
        {materias?.length > 0 ? (
          <div className="divide-y divide-gray-50">
            {materias.map((materia) => (
              <div key={materia.materiaId} className="p-4 flex justify-between items-center group">
                <p className="text-sm font-medium text-gray-700">{materia.materiaNombre}</p>
                <button
                  onClick={() => onDesaprobar(materia.materiaId)}
                  className="p-1.5 text-red-400 hover:text-red-600 hover:bg-red-50 rounded-full transition-colors"
                  title="Quitar de aprobadas"
                >
                  <Trash2 size={16} />
                </button>
              </div>
            ))}
          </div>
        ) : (
          <div className="py-10 text-center text-gray-400 italic text-sm">
            No hay registros de materias aprobadas.
          </div>
        )}
      </div>
    </section>
  );
}