import MateriasSelector from "../Materia/MateriasSelector"
import { useFormComision } from "../../hooks/useComision"

export default function ComisionSelectorMateria() {
  const { comision, setComision } = useFormComision()

  return (
    <div>
      <h2 className="text-xl text-white font-bold mb-4">
        Seleccionar Materia para la Comisión
      </h2>
      <MateriasSelector
        buscadorClassName="max-w-sm"
        selectedId={comision.materiaSeleccionada?.materiaId}
        onClick={(m) => {
          setComision((prev) => ({
            ...prev,
            materiaSeleccionada: m,
            comision: { ...prev.comision, materiaId: m.materiaId },
          }))
        }}
      />
    </div>
  )
}
