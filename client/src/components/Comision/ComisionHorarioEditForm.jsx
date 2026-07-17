import { useState } from "react"
import { Trash2, Edit, Save } from "feather-icons-react"
import { useFormComision } from "../../hooks/useComision"
import { useComision } from "../../hooks/useComision"
import Modal from "../Ui/Modal/Modal"

const DIAS = [
  { label: "Lunes", value: "LUNES" },
  { label: "Martes", value: "MARTES" },
  { label: "Miércoles", value: "MIERCOLES" },
  { label: "Jueves", value: "JUEVES" },
  { label: "Viernes", value: "VIERNES" },
  { label: "Sábado", value: "SABADO" },
]

export default function ComisionHorarioEditForm({
  titulo = "Modificar Horarios de Cursada",
}) {
  const { comision, setHorariosComision } = useFormComision()
  const [editingIndex, setEditingIndex] = useState(null)
  const [error, setError] = useState("")
  const [editValues, setEditValues] = useState({
    dia: "",
    horaInicio: "",
    horaFin: "",
  })
  const { eliminarHorarioComision } = useComision()

  const [modalOpen, setModalOpen] = useState(false)
  const [modalAction, setModalAction] = useState(null)
  const [pendingIndex, setPendingIndex] = useState(null)

  const horarios = comision.comision.horarios

  const iniciarEdicion = (index, horario) => {
    setEditingIndex(index)
    setEditValues({
      dia: horario.dia,
      horaInicio: horario.horaInicio,
      horaFin: horario.horaFin,
    })
    setError("")
  }

  const cancelarEdicion = () => {
    setEditingIndex(null)
    setEditValues({ dia: "", horaInicio: "", horaFin: "" })
    setError("")
  }

  const guardarEdicion = (index) => {
    if (!editValues.dia || !editValues.horaInicio || !editValues.horaFin) {
      setError("Completá todos los campos.")
      return
    }

    if (editValues.horaInicio >= editValues.horaFin) {
      setError("La hora de inicio debe ser anterior a la de fin.")
      return
    }

    setModalAction("guardar")
    setPendingIndex(index)
    setModalOpen(true)
  }

  const confirmarGuardar = () => {
    const horariosActualizados = horarios.map((h, i) =>
      i === pendingIndex
        ? {
            ...h,
            dia: editValues.dia,
            horaInicio: editValues.horaInicio,
            horaFin: editValues.horaFin,
            id: h.id,
          }
        : h
    )
    setHorariosComision(horariosActualizados)
    setEditingIndex(null)
    setEditValues({ dia: "", horaInicio: "", horaFin: "" })
    setModalOpen(false)
    setPendingIndex(null)
  }

  const eliminarHorario = (index) => {
    setModalAction("eliminar")
    setPendingIndex(index)
    setModalOpen(true)
  }

  const confirmarEliminar = () => {
    const horarioId = horarios[pendingIndex].id
    eliminarHorarioComision(horarioId)
    setHorariosComision(horarios.filter((_, i) => i !== pendingIndex))
    setModalOpen(false)
    setPendingIndex(null)
  }

  return (
    <div className="bg-white/5 border border-white/10 rounded-xl p-6 backdrop-blur-sm w-full max-w-2xl">
      <h2 className="text-xl text-white font-bold mb-5">{titulo}</h2>

      {error && <p className="text-red-400 text-sm mb-4">{error}</p>}

      {horarios.length > 0 ? (
        <ul className="flex flex-col gap-3">
          {horarios.map((horario, index) =>
            editingIndex === index ? (
              <li
                key={index}
                className="flex flex-wrap items-end gap-3 bg-gray-900/60 border border-red-500/30 rounded-lg p-4"
              >
                <div className="flex flex-col gap-1">
                  <label className="text-xs text-gray-400 font-medium">
                    Día
                  </label>
                  <select
                    value={editValues.dia}
                    onChange={(e) =>
                      setEditValues({ ...editValues, dia: e.target.value })
                    }
                    className="rounded-lg border border-gray-600 bg-gray-800 text-white px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-transparent transition"
                  >
                    {DIAS.map((d) => (
                      <option key={d.value} value={d.value}>
                        {d.label}
                      </option>
                    ))}
                  </select>
                </div>

                <div className="flex flex-col gap-1">
                  <label className="text-xs text-gray-400 font-medium">
                    Inicio
                  </label>
                  <input
                    type="time"
                    value={editValues.horaInicio}
                    onChange={(e) =>
                      setEditValues({
                        ...editValues,
                        horaInicio: e.target.value,
                      })
                    }
                    className="rounded-lg border border-gray-600 bg-gray-800 text-white px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-transparent transition"
                  />
                </div>

                <div className="flex flex-col gap-1">
                  <label className="text-xs text-gray-400 font-medium">
                    Fin
                  </label>
                  <input
                    type="time"
                    value={editValues.horaFin}
                    onChange={(e) =>
                      setEditValues({ ...editValues, horaFin: e.target.value })
                    }
                    className="rounded-lg border border-gray-600 bg-gray-800 text-white px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-transparent transition"
                  />
                </div>

                <div className="flex gap-2">
                  <button
                    onClick={() => guardarEdicion(editingIndex)}
                    className="flex items-center gap-1 bg-green-600 hover:bg-green-700 text-white font-semibold px-3 py-2 rounded-lg transition-colors text-sm"
                    title="Guardar cambios"
                  >
                    <Save size={16} />
                    Guardar
                  </button>
                  <button
                    onClick={cancelarEdicion}
                    className="px-3 py-2 text-gray-300 hover:text-white border border-gray-600 rounded-lg transition-colors text-sm"
                  >
                    Cancelar
                  </button>
                </div>
              </li>
            ) : (
              <li
                key={index}
                className="flex items-center justify-between bg-gray-800/60 border border-gray-700 rounded-lg px-4 py-3 hover:bg-gray-800 transition-colors group"
              >
                <span
                  className="text-white text-sm font-medium cursor-pointer flex-1"
                  onClick={() => iniciarEdicion(index, horario)}
                >
                  {DIAS.find((d) => d.value === horario.dia)?.label ||
                    horario.dia}{" "}
                  — {horario.horaInicio} a {horario.horaFin}
                </span>
                <div className="flex gap-2 opacity-0 group-hover:opacity-100 transition-opacity">
                  <button
                    onClick={() => iniciarEdicion(index, horario)}
                    className="text-blue-400 hover:text-blue-300 transition-colors"
                    aria-label="Editar horario"
                    title="Editar"
                  >
                    <Edit size={16} />
                  </button>
                  <button
                    onClick={() => eliminarHorario(index)}
                    className="text-red-400 hover:text-red-300 transition-colors"
                    aria-label="Eliminar horario"
                    title="Eliminar"
                  >
                    <Trash2 size={16} />
                  </button>
                </div>
              </li>
            )
          )}
        </ul>
      ) : (
        <p className="text-gray-500 text-sm">No hay horarios para editar.</p>
      )}

      <Modal isOpen={modalOpen} onClose={() => setModalOpen(false)}>
        <div className="flex flex-col gap-4">
          {modalAction === "guardar" && (
            <>
              <h2 className="text-2xl font-bold text-gray-900">
                Confirmar guardar horario
              </h2>
              <p className="text-gray-600">
                ¿Estás seguro de que querés guardar los cambios en este horario?
              </p>
              <div className="flex gap-3 justify-end mt-4">
                <button
                  onClick={() => setModalOpen(false)}
                  className="px-6 py-2.5 border border-gray-300 rounded-lg text-gray-700 font-semibold hover:bg-gray-50 transition-colors"
                >
                  Cancelar
                </button>
                <button
                  onClick={confirmarGuardar}
                  className="px-6 py-2.5 bg-green-600 hover:bg-green-700 text-white font-semibold rounded-lg transition-colors"
                >
                  Guardar
                </button>
              </div>
            </>
          )}

          {modalAction === "eliminar" && (
            <>
              <h2 className="text-2xl font-bold text-gray-900">
                Confirmar eliminar horario
              </h2>
              <p className="text-gray-600">
                ¿Estás seguro de que querés eliminar este horario? Esta acción
                no se puede deshacer.
              </p>
              <div className="flex gap-3 justify-end mt-4">
                <button
                  onClick={() => setModalOpen(false)}
                  className="px-6 py-2.5 border border-gray-300 rounded-lg text-gray-700 font-semibold hover:bg-gray-50 transition-colors"
                >
                  Cancelar
                </button>
                <button
                  onClick={confirmarEliminar}
                  className="px-6 py-2.5 bg-red-600 hover:bg-red-700 text-white font-semibold rounded-lg transition-colors"
                >
                  Eliminar
                </button>
              </div>
            </>
          )}
        </div>
      </Modal>
    </div>
  )
}
