export function CursadaItem({ comision }) {
  return (
    <div className="p-4 hover:bg-gray-50 transition-colors flex flex-col md:flex-row md:justify-between md:items-center gap-3">
      <div className="space-y-1">
        <div className="flex items-center gap-2">
          <p className="font-bold text-gray-800 leading-tight">
            {comision.materia?.materiaNombre}
          </p>
          <span className="text-[9px] bg-red-50 text-red-900 px-1.5 py-0.5 rounded font-bold border border-red-100">
            C{comision.comisionId}
          </span>
        </div>
        
        <div className="flex flex-wrap gap-1.5">
          {comision.claseHorario?.map((clase, idx) => (
            <span key={idx} className="text-[10px] text-gray-500 bg-gray-100 px-2 py-0.5 rounded font-medium">
              {clase.dia}: {clase.inicio.slice(0, 5)} - {clase.fin.slice(0, 5)}hs
            </span>
          ))}
        </div>
      </div>

      <div className="flex items-center gap-3 self-end md:self-center">
        <div className="text-right">
          <span className="text-[10px] bg-gray-800 text-white px-2 py-1 rounded font-bold tracking-wider">
            AULA: {comision.aula || 'S/A'}
          </span>
        </div>
      </div>
    </div>
  );
}