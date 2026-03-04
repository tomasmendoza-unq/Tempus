import MateriasSelector from "../Materia/MateriasSelector"

export default function ComisionSelectorMateria({
  setMateriaSeleccionada,
  selectedId,
}) {
  return (
    <div>
      <h2 className="text-xl text-white font-bold mb-4">
        Seleccionar Materia para la Comisión
      </h2>
      <MateriasSelector
        buscadorClassName="max-w-sm"
        selectedId={selectedId}
        onClick={(m) => {
          setMateriaSeleccionada((prev) => ({
            ...prev,
            materiaSeleccionada: m,
            comision: { ...prev.comision, materiaId: m.materiaId },
          }))
        }}
      />
    </div>
  )
}
