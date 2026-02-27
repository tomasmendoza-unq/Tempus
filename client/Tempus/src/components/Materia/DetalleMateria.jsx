export default function Materia({ materia }) {
  return (
    <>
      <div className="flex flex-col items-start justify-center gap-4 p-6">
        <div>
          <h2 className="text-xl font-bold">{materia.materiaNombre}</h2>
        </div>
        <div className="grid grid-cols-2 gap-3">
          <div>
            <h4>Carrera: {materia.carrera}</h4>
          </div>
          <div>
            <strong>Id:</strong> {materia.materiaId}
          </div>
          <strong>Correlativas:</strong>
          <li>
            {materia.correlativas && materia.correlativas.length > 0 ? (
              <ul>
                {materia.correlativas.map((correlativa) => (
                  <li key={correlativa.materiaId}>
                    {correlativa.materiaNombre}
                  </li>
                ))}
              </ul>
            ) : (
              <p>No tiene correlativas.</p>
            )}
          </li>
          <strong>Comisiones:</strong>
          <li>
            {materia.comisiones && materia.comisiones.length > 0
              ? materia.comisiones.map((c) => c.materiaNombre).join(", ")
              : "No tiene comisiones disponibles."}
          </li>
        </div>
      </div>
    </>
  )
}
