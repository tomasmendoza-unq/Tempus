import { HorarioGrid } from "./HorarioGrid";

export function HorarioModalContent({ horarioData }) {
  return (
    <div className="w-full max-w-6xl min-w-[900px] mt-4">
      <div className="mb-6 border-b pb-4">
        <h2 className="text-2xl font-extrabold text-red-950 italic">Cronograma de Clases</h2>
        <p className="text-gray-500 text-xs uppercase tracking-widest font-bold">
          Tempus - Ciclo Lectivo 2026
        </p>
      </div>
      <HorarioGrid horario={horarioData} />
    </div>
  );
}