const DIAS = ["LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO"];
const HORAS = [
  "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", 
  "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"
];

export function HorarioGrid({ horario }) {
  
  const getMateriaEnSlot = (dia, hora) => {
    if (!horario?.comisiones) return null;

    for (const comision of horario.comisiones) {
      if (!comision.claseHorario) continue;

      for (const clase of comision.claseHorario) {
        const diaNormalizado = clase.dia
          .toUpperCase()
          .normalize("NFD")
          .replace(/[\u0300-\u036f]/g, "");
        
        const diaColumna = dia.normalize("NFD").replace(/[\u0300-\u036f]/g, "");

        if (diaNormalizado === diaColumna && clase.inicio.includes(hora)) {
          return comision;
        }
      }
    }
    return null;
  };

  return (
    <div className="overflow-x-auto border rounded-xl shadow-lg bg-white p-2">
      <table className="min-w-full table-fixed border-collapse">
        <thead>
          <tr>
            <th className="border-b p-3 bg-gray-100 text-gray-700 text-xs font-bold uppercase w-20">
              Hora
            </th>
            {DIAS.map((d) => (
              <th key={d} className="border-b p-3 bg-gray-100 text-gray-700 text-xs font-bold uppercase">
                {d}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {HORAS.map((hora) => (
            <tr key={hora} className="hover:bg-gray-50 transition-colors">
              <td className="border-r border-b p-2 text-sm font-bold font-mono text-gray-600 text-center bg-gray-50">
                {hora}
              </td>
              {DIAS.map((dia) => {
                const comision = getMateriaEnSlot(dia, hora);
                
                return (
                  <td
                    key={`${dia}-${hora}`}
                    className={`border-r border-b p-1.5 h-16 w-32 transition-all ${
                      comision ? "bg-red-50" : "bg-transparent"
                    }`}
                  >
                    {comision && (
                      <div className="flex flex-col h-full justify-center text-center p-2 rounded border border-red-200 bg-white shadow-sm">

                        <span className="text-[10px] font-black text-red-950 uppercase leading-tight mb-1">
                          {comision.materia?.materiaNombre || "Materia"}
                        </span>
                        <div className="flex justify-between items-center mt-auto">
                          <span className="text-[9px] text-gray-500 font-bold">
                            C{comision.comisionId}
                          </span>
                          <span className="text-[9px] bg-red-100 text-red-700 px-1.5 py-0.5 rounded font-bold">
                            {comision.aula || 'S/A'}
                          </span>
                        </div>
                      </div>
                    )}
                  </td>
                );
              })}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}