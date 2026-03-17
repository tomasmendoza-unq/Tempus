import MateriasSelector from "../Materia/MateriasSelector"

export default function ComisionSelectorMateria({
  setMateriaSeleccionada,
  selectedId,
}) {
  return (
    <div className="w-full">
      <h2 className="text-2xl text-white font-bold mb-6 flex items-center gap-2">
        Seleccionar Materia para la Comisión
      </h2>
      <MateriasSelector
        buscadorClassName="w-full" 
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