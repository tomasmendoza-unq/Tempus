export default function Materia({ materia }) {
  return (
    <>
      <div>
        <h2>{materia.materiaNombre}</h2>
      </div>
      <div>
        <strong>Id:</strong> {materia.materiaId}
      </div>
      <li>
        {materia.correlativas && materia.correlativas.length > 0 ? (
          <ul>
            {materia.correlativas.map((correlativa) => (
              <li key={correlativa.materiaId}>{correlativa.materiaNombre}</li>
            ))}
          </ul>
        ) : (
          <p>No tiene correlativas.</p>
        )}
      </li>
      <li>
        <strong>Correlativas:</strong>{" "}
        {materia.comisiones && materia.comisiones.length > 0
          ? materia.comisiones.map((c) => c.materiaNombre).join(", ")
          : "No tiene comisiones disponibles."}
      </li>
    </>
  )
}
