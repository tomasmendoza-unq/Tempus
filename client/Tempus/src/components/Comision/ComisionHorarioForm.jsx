import { useState } from "react"
import { Plus, Trash2 } from "feather-icons-react"

const DIAS = [
  { label: "Lunes", value: "LUNES" },
  { label: "Martes", value: "MARTES" },
  { label: "Miércoles", value: "MIERCOLES" },
  { label: "Jueves", value: "JUEVES" },
  { label: "Viernes", value: "VIERNES" },
  { label: "Sábado", value: "SABADO" },
]

export default function ComisionHorarioForm({ comision, setComision }) {
  const [dia, setDia] = useState("")
  const [horaInicio, setHoraInicio] = useState("")
  const [horaFin, setHoraFin] = useState("")
  const [error, setError] = useState("")

  const horarios = comision.comision.horarios

  const agregarHorario = () => {
    if (!dia || !horaInicio || !horaFin) {
      setError("Completá todos los campos.")
      return
    }
    if (horaInicio >= horaFin) {
      setError("La hora de inicio debe ser anterior a la de fin.")
      return
    }

    const yaExiste = horarios.some(
      (h) =>
        h.dia === dia && h.horaInicio === horaInicio && h.horaFin === horaFin
    )
    if (yaExiste) {
      setError("Este horario ya fue agregado.")
      return
    }

    setError("")
    setComision((prev) => ({
      ...prev,
      comision: {
        ...prev.comision,
        horarios: [...prev.comision.horarios, { dia, horaInicio, horaFin }],
      },
    }))
    setDia("")
    setHoraInicio("")
    setHoraFin("")
  }

  const eliminarHorario = (index) => {
    setComision((prev) => ({
      ...prev,
      comision: {
        ...prev.comision,
        horarios: prev.comision.horarios.filter((_, i) => i !== index),
      },
    }))
  }

  return (
    <div className="bg-white/5 border border-white/10 rounded-xl p-6 backdrop-blur-sm">
      <h2 className="text-xl text-white font-bold mb-5">
        Agregar Horarios de Cursada
      </h2>

      <div className="flex flex-wrap items-end gap-4 mb-4">
        <div className="flex flex-col gap-1">
          <label className="text-sm text-gray-300 font-medium">Día</label>
          <select
            value={dia}
            onChange={(e) => setDia(e.target.value)}
            className="rounded-lg border border-gray-600 bg-gray-800 text-white px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-transparent transition"
          >
            <option value="">Seleccionar...</option>
            {DIAS.map((d) => (
              <option key={d.value} value={d.value}>
                {d.label}
              </option>
            ))}
          </select>
        </div>

        <div className="flex flex-col gap-1">
          <label className="text-sm text-gray-300 font-medium">
            Hora inicio
          </label>
          <input
            type="time"
            value={horaInicio}
            onChange={(e) => setHoraInicio(e.target.value)}
            className="rounded-lg border border-gray-600 bg-gray-800 text-white px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-transparent transition"
          />
        </div>

        <div className="flex flex-col gap-1">
          <label className="text-sm text-gray-300 font-medium">Hora fin</label>
          <input
            type="time"
            value={horaFin}
            onChange={(e) => setHoraFin(e.target.value)}
            className="rounded-lg border border-gray-600 bg-gray-800 text-white px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-transparent transition"
          />
        </div>

        <button
          onClick={agregarHorario}
          className="flex items-center gap-1 bg-red-600 hover:bg-red-700 text-white font-semibold px-4 py-2 rounded-lg transition-colors"
        >
          <Plus size={16} />
          Agregar
        </button>
      </div>

      {error && <p className="text-red-400 text-sm mb-4">{error}</p>}

      {horarios.length > 0 ? (
        <ul className="flex flex-col gap-2 mt-4">
          {horarios.map((h, i) => (
            <li
              key={i}
              className="flex items-center justify-between bg-gray-800/60 border border-gray-700 rounded-lg px-4 py-3"
            >
              <span className="text-white text-sm font-medium">
                {DIAS.find((d) => d.value === h.dia)?.label || h.dia} —{" "}
                {h.horaInicio} a {h.horaFin}
              </span>
              <button
                onClick={() => eliminarHorario(i)}
                className="text-red-400 hover:text-red-300 transition-colors"
                aria-label="Eliminar horario"
              >
                <Trash2 size={16} />
              </button>
            </li>
          ))}
        </ul>
      ) : (
        <p className="text-gray-500 text-sm mt-2">
          No hay horarios agregados todavía.
        </p>
      )}
    </div>
  )
}
