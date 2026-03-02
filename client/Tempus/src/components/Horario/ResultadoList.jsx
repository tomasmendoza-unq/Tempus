import { HorarioGrid } from "./HorarioGrid";

export function ResultadoList({ resultados, cargando }) {
  if (!cargando && (!resultados || resultados.length === 0)) {
    return (
      <div className="col-span-full text-center py-10 text-gray-400 italic">
        No hay combinaciones para mostrar. Seleccioná materias y hacé clic en Generar.
      </div>
    );
  }

  return (
    <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
      {resultados.map((horario, index) => (
        <div key={index} className="space-y-3 animate-fadeIn">
          <div className="flex items-center gap-2">
            <span className="flex items-center justify-center bg-red-950 text-white w-8 h-8 rounded-full text-sm font-bold">
              {index + 1}
            </span>
            <h4 className="font-semibold text-gray-700">Opción de Horario</h4>
          </div>
          <HorarioGrid horario={horario} />
        </div>
      ))}
    </div>
  );
}