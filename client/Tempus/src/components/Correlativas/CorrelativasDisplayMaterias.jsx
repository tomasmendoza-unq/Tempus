export default function CorrelativasDisplayMaterias({
  materias,
  onClick,
  selectedId,
  selectedIds,
  excludedId,
}) {
  const isSelectedFn = (id) =>
    selectedIds ? selectedIds.includes(id) : id === selectedId
  const hasSelection = selectedIds ? selectedIds.length > 0 : selectedId != null

  return (
    <div>
      <ul className="overflow-y-auto max-h-64 custom-scrollbar">
        {materias.map((materia) => {
          const isSelected = isSelectedFn(materia.materiaId)
          const isExcluded = Array.isArray(excludedId)
            ? excludedId.includes(materia.materiaId)
            : materia.materiaId === excludedId
          const isDimmed = hasSelection && !isSelected
          return (
            <li
              className={`p-3 border border-grey-100 m-3 rounded transition-opacity ${
                isDimmed || isExcluded ? "opacity-40" : "opacity-100"
              } ${isSelected ? "ring-2 ring-red-950" : ""} ${
                isExcluded ? "cursor-not-allowed" : "cursor-pointer"
              }`}
              onClick={() => !isExcluded && onClick(materia)}
              key={materia.materiaId}
            >
              {materia.materiaNombre}
            </li>
          )
        })}
      </ul>
    </div>
  )
}
