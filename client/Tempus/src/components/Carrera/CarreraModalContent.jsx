import { BookOpen } from "feather-icons-react";

export function CarreraModalContent({ carrerasDisponibles, onSeleccionar }) {
  return (
    <div className="w-full max-w-2xl min-w-[500px] mt-4">
      <div className="mb-6 border-b pb-4">
        <h2 className="text-2xl font-extrabold text-red-950 italic">Oferta Académica</h2>
        <p className="text-gray-500 text-xs uppercase tracking-widest font-bold">
          Tempus - Inscripción Ciclo 2026
        </p>
      </div>
      
      <div className="space-y-3 max-h-[400px] overflow-y-auto pr-2 custom-scrollbar">
        {carrerasDisponibles?.length > 0 ? (
          carrerasDisponibles.map((carrera) => (
            <div 
              key={carrera.id} 
              className="flex items-center justify-between p-4 bg-white rounded-xl border border-gray-100 hover:border-red-200 hover:shadow-md transition-all group"
            >
              <div className="flex items-center gap-4">
                <div className="p-2 bg-red-50 rounded-lg text-red-950 group-hover:bg-red-950 group-hover:text-white transition-colors">
                  <BookOpen className="w-5 h-5" />
                </div>
                <div className="flex flex-col">
                  <span className="font-bold text-gray-800 group-hover:text-red-950 transition-colors">
                    {carrera.nombreCarrera}
                  </span>
                  <span className="text-[10px] text-gray-400 uppercase font-semibold">
                    UNQ - Código {carrera.id}
                  </span>
                </div>
              </div>

              <button
                onClick={() => onSeleccionar(carrera.id)}
                className="bg-red-950 text-white px-5 py-2 rounded-lg text-xs font-black uppercase tracking-tight hover:bg-red-800 transition-colors shadow-sm active:scale-95"
              >
                Inscribirme
              </button>
            </div>
          ))
        ) : (
          <div className="py-12 text-center">
            <p className="text-gray-400 italic text-sm">No hay más carreras disponibles para tu legajo.</p>
          </div>
        )}
      </div>
    </div>
  );
}