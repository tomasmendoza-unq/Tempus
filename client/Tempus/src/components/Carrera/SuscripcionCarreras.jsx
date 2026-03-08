import { Book, Plus, Trash2 } from "feather-icons-react";

export function SuscripcionCarreras({ carrerasUsuario, onDesuscribir, onSuscribir }) {
  return (
    <section className="space-y-4">
      <div className="flex items-center justify-between">
        <div className="flex items-center gap-2 text-red-950">
          <Book className="w-5 h-5" />
          <h3 className="text-lg font-bold">Mis Carreras</h3>
        </div>
        <button 
          onClick={onSuscribir}
          className="flex items-center gap-1 text-sm bg-red-950 text-white px-3 py-1.5 rounded-lg hover:bg-red-800 transition-colors shadow-sm"
        >
          <Plus className="w-4 h-4" />
          Nueva Carrera
        </button>
      </div>

      <div className="bg-white rounded-xl border border-gray-100 shadow-sm overflow-hidden">
        {carrerasUsuario?.length > 0 ? (
          <div className="divide-y divide-gray-50">
            {carrerasUsuario.map((carrera) => (
              <div 
                key={carrera.idCarrera} 
                className="flex items-center justify-between p-4 hover:bg-gray-50 transition-colors"
              >
                <div className="flex flex-col">
                  <span className="font-semibold text-gray-800">
                    {carrera.nombreCarrera}
                  </span>
                  <span className="text-xs text-gray-500 uppercase tracking-wider">
                    Código: {carrera.idCarrera}
                  </span>
                </div>
                
                <button
                  onClick={() => onDesuscribir?.(carrera.idCarrera)}
                  className="p-2 text-gray-400 hover:text-red-600 hover:bg-red-50 rounded-full transition-all"
                  title="Dar de baja carrera"
                >
                  <Trash2 className="w-4 h-4" />
                </button>
              </div>
            ))}
          </div>
        ) : (
          <div className="py-10 text-center text-gray-400 italic text-sm">
            Aún no estás suscripto a ninguna carrera.
          </div>
        )}
      </div>
    </section>
  );
}