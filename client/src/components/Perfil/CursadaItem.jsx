import { CheckCircle } from "feather-icons-react"; 

export function CursadaItem({ comision, onAprobar }) {
  return (
    <div className="p-4 hover:bg-gray-50 transition-colors flex justify-between items-start gap-4 group">

      <div className="flex-1 space-y-2">
        <div className="flex items-center gap-2">
          <p className="font-bold text-gray-800 leading-tight">
            {comision.materia?.materiaNombre}
          </p>
          <span className="text-[9px] bg-red-50 text-red-900 px-1.5 py-0.5 rounded font-bold border border-red-100">
            C{comision.comisionId}
          </span>
        </div>
        
        <div className="flex flex-col gap-1">
          {comision.claseHorario?.map((clase, idx) => (
            <span key={idx} className="text-[10px] text-gray-500 font-medium uppercase tracking-tighter">
              {clase.dia}: {clase.inicio.slice(0, 5)} - {clase.fin.slice(0, 5)}hs
            </span>
          ))}
        </div>
      </div>

      <div className="flex flex-col items-end gap-3 min-w-[100px]">
        <div className="bg-gray-800 text-white text-[9px] px-2 py-1 rounded font-black tracking-widest flex items-center justify-center whitespace-nowrap">
          AULA: {comision.aula || 'S/A'}
        </div>
        
        <button
          onClick={() => onAprobar(comision.comisionId)}
          className="p-1.5 text-green-600 hover:bg-green-50 rounded-full transition-all hover:scale-110 active:scale-90"
          title="Marcar como aprobada"
        >
          <CheckCircle size={22} />
        </button>
      </div>
    </div>
  );
}