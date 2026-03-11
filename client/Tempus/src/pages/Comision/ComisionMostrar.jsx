import ComisionCard from "../../components/Comision/ComisionCard"

const comisionesHardcodeadas = [
  {
    comisionId: "C001",
    materia: { materiaNombre: "Algoritmos y Estructuras de Datos" },
    claseHorario: "Lunes 08:00 - 12:00",
  },
  {
    comisionId: "C002",
    materia: { materiaNombre: "Base de Datos" },
    claseHorario: "Martes 14:00 - 18:00",
  },
  {
    comisionId: "C003",
    materia: { materiaNombre: "Sistemas Operativos" },
    claseHorario: "Miércoles 10:00 - 14:00",
  },
  {
    comisionId: "C004",
    materia: { materiaNombre: "Redes de Computadoras" },
    claseHorario: "Jueves 16:00 - 20:00",
  },
  {
    comisionId: "C005",
    materia: { materiaNombre: "Ingeniería de Software" },
    claseHorario: "Viernes 08:00 - 12:00",
  },
]

export default function ComisionMostrar() {
  return (
    <div>
      <h1 className="text-2xl text-white font-bold mb-4">Comisiones</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {comisionesHardcodeadas.map((comision) => (
          <ComisionCard key={comision.comisionId} comision={comision} />
        ))}
      </div>
    </div>
  )
}
