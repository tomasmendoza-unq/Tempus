export const DetalleMateria = ({ materia }) => {
  if (!materia) return (
    <div className="flex items-center justify-center h-full text-gray-400 italic">
      Seleccioná una materia para ver sus detalles
    </div>
  );

  return (
    <>
      <div className="bg-red-950 p-4">
        <h2 className="text-white font-bold uppercase tracking-tight">{materia.materiaNombre}</h2>
      </div>
      <div className="p-6 overflow-y-auto grid grid-cols-1 sm:grid-cols-2 gap-4">
        {materia.comisiones.map((com, idx) => (
          <div key={idx} className="bg-gray-50 p-4 rounded-xl border border-gray-200 hover:shadow-md transition-shadow">
            <div className="flex justify-between items-start mb-3">
              <span className="font-bold text-gray-800">{com.comisionNombre}</span>
              <span className="text-[10px] px-2 py-0.5 bg-white border border-red-200 text-red-900 rounded-full font-bold uppercase">
                {com.modalidad}
              </span>
            </div>
            <div className="space-y-2">
              {com.claseHorario.map((horario, hIdx) => (
                <div key={hIdx} className="flex items-center text-xs">
                  <span className="w-20 font-bold text-gray-400 uppercase">{horario.dia}</span>
                  <span className="bg-white px-3 py-1 rounded-md border border-gray-100 font-medium text-gray-700">
                    {horario.inicio.substring(0, 5)} - {horario.fin.substring(0, 5)}
                  </span>
                </div>
              ))}
            </div>
          </div>
        ))}
      </div>
    </>
  );
};