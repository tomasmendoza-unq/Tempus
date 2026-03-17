export const parseBackendComisionToReducer = (response) => {
  if (!response) {
    return null
  }

  const horariosTransformados =
    response.claseHorario?.map((h) => ({
      dia: h.dia,
      horaInicio: h.inicio,
      horaFin: h.fin,
      id: h.id,
    })) || []

  return {
    comision: {
      comisionId: response.comisionId,
      materiaId: response.materia?.materiaId ?? response.materiaId,
      horarios: horariosTransformados,
    },
    materiaSeleccionada: response.materia,
    step: 0,
  }
}
