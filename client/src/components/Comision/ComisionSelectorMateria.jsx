import MateriasSelector from "../Materia/MateriasSelector"
import { useFormComision } from "../../hooks/useComision"

export default function ComisionSelectorMateria() {
  const { comision, setComision } = useFormComision()

  return (
    <div className="w-full">
      <h2 className="text-2xl text-white font-bold mb-6 flex items-center gap-2">
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