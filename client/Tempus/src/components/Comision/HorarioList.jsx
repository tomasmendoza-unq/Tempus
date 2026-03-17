export default function HorarioList({ horarios = [] }) {
  if (!horarios.length) return <p className="text-sm text-gray-400">Sin horarios</p>

  return (
    <div className="space-y-2">
      {horarios.map((h, i) => (
        <div key={i} className="flex items-center text-xs">
          <span className="w-20 font-bold text-gray-400 uppercase">{h.dia}</span>
          <span className="bg-white px-3 py-1 rounded-md border border-gray-100 font-medium text-gray-700">
            {h.inicio?.substring(0, 5)} - {h.fin?.substring(0, 5)}
          </span>
        </div>
      ))}
    </div>
  )
}