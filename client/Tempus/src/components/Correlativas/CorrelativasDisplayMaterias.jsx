export default function CorrelativasDisplayMaterias({
  materias,
  onClick,
  selectedId,
  excludedId,
}) {
  return (
    <div>
      <ul className="overflow-y-auto max-h-64 custom-scrollbar">
        {materias.map((materia) => {
          const isSelected = materia.materiaId === selectedId
          const isExcluded = materia.materiaId === excludedId
          const isDimmed = selectedId != null && !isSelected
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
