import TarjetaMateria from "../Materia/TarjetaMateria"
import Modal from "../Ui/Modal/Modal"
import DetalleMateria from "../Materia/DetalleMateria"
import { useTraerMateria } from "../../hooks/useMateria"
import { List } from "feather-icons-react"
import { useState } from "react"

const DIAS_LABELS = {
  LUNES: "Lunes",
  MARTES: "Martes",
  MIERCOLES: "Miércoles",
  JUEVES: "Jueves",
  VIERNES: "Viernes",
  SABADO: "Sábado",
}

export default function ComisionReview({ comision }) {
  const { materiaSeleccionada } = comision
  const { horarios } = comision.comision
  const [isModalOpen, setIsModalOpen] = useState(false)
  const { traerMateria, materia } = useTraerMateria()

  return (
    <div className="bg-white/5 border border-white/10 rounded-xl p-6 backdrop-blur-sm">
      <h2 className="text-xl text-white font-bold mb-5">
        Revisión de la Comisión
      </h2>

      <div className="mb-6">
        <h3 className="text-sm text-gray-400 uppercase tracking-wide font-semibold mb-3">
          Materia
        </h3>
        {materiaSeleccionada ? (
          <TarjetaMateria materia={materiaSeleccionada}>
            <button
              className="mt-2 text-green-500 hover:text-green-700 flex items-center gap-1"
              onClick={async () => {
                await traerMateria(materiaSeleccionada.materiaId)
                setIsModalOpen(true)
              }}
            >
              <List size={16} />
              <span>Ver detalles</span>
            </button>
            <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)}>
              <DetalleMateria materia={materia} />
            </Modal>
          </TarjetaMateria>
        ) : (
          <p className="text-red-400 text-sm">No se seleccionó una materia.</p>
        )}
      </div>

      <div>
        <h3 className="text-sm text-gray-400 uppercase tracking-wide font-semibold mb-3">
          Horarios
        </h3>
        {horarios.length > 0 ? (
          <ul className="flex flex-col gap-2">
            {horarios.map((h, i) => (
              <li
                key={i}
                className="flex items-center bg-gray-800/60 border border-gray-700 rounded-lg px-4 py-3"
              >
                <span className="text-white text-sm font-medium">
                  {DIAS_LABELS[h.dia] || h.dia} — {h.horaInicio} a {h.horaFin}
                </span>
              </li>
            ))}
          </ul>
        ) : (
          <p className="text-red-400 text-sm">No se agregaron horarios.</p>
        )}
      </div>
    </div>
  )
}
