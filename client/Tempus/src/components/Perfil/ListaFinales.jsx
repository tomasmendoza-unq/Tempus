
export function ListaFinales({ materias }) {
  return (
    <section className="space-y-4">
      <h3 className="text-xl font-bold text-red-950 flex items-center gap-2 italic">
        <span className="w-1.5 h-6 bg-red-900 rounded-full"></span>
        Finales Aprobados
      </h3>
      <div className="bg-white rounded-xl border border-gray-200 shadow-sm overflow-hidden">
        {materias?.length > 0 ? (
          materias.map((mat) => (
            <div key={mat.materiaId} className="p-4 border-b last:border-0 flex justify-between items-center hover:bg-gray-50">
              <span className="font-semibold text-gray-700">{mat.materiaNombre}</span>
              <span className="bg-green-100 text-green-700 text-[10px] px-2 py-0.5 rounded-full font-black border border-green-200 shadow-sm">
                APROBADA
              </span>
            </div>
          ))
        ) : (
          <p className="p-4 text-gray-400 text-sm italic">No hay registros de materias aprobadas.</p>
        )}
      </div>
    </section>
  );
}