import TarjetaMateria from "../Materia/TarjetaMateria"
import { X } from "feather-icons-react"

export default function ComisionMateriaSeleccionada({
  materiaSeleccionada,
  setMateriaSeleccionada,
}) {
  return (
    <div>
      <h2 className="text-xl text-white font-bold mb-4">
        Materia Seleccionada para la Comisión
      </h2>
      <TarjetaMateria materia={materiaSeleccionada}>
        <button
          onClick={() =>
            setMateriaSeleccionada((prev) => ({
              ...prev,
              materiaSeleccionada: null,
              comision: { ...prev.comision, materiaId: null },
            }))
          }
          className="absolute top-2 right-2 p-1 rounded-full text-red-400 hover:text-white hover:bg-red-500 transition-all duration-200"
          aria-label="Quitar materia"
        >
          <X size={16} />
        </button>
      </TarjetaMateria>
    </div>
  )
}
