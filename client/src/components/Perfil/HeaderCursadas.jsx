import { Calendar } from "feather-icons-react";

export function HeaderCursadas({ onOpenModal }) {
  return (
    <div className="flex items-center justify-between px-1">
      <h3 className="text-xl font-bold text-red-950 flex items-center gap-2 italic">
        <span className="w-1.5 h-6 bg-red-900 rounded-full"></span>
        Cursadas Actuales
      </h3>
      <button 
        onClick={onOpenModal}
        className="flex items-center gap-2 text-[10px] font-black uppercase tracking-widest bg-red-950 text-white px-4 py-2 rounded-lg hover:bg-red-900 transition-all shadow-md active:scale-95"
      >
        <Calendar size={14} />
        Ver Horario Completo
      </button>
    </div>
  );
}