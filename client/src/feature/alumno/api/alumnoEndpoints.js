const BASE = "/alumno"

export const ALUMNO_ENDPOINTS = {
	GET_ALUMNO: BASE,
	POST_SUSCRIBIRSE_CARRERA: `${BASE}/carreras/suscribirse`,
	POST_DESUSCRIBIRSE_CARRERA: `${BASE}/carreras/desuscribirse`,
	POST_DESAPROBAR_MATERIA: (idMateria) =>
		`${BASE}/materia/desaprobar/${idMateria}`,
	POST_APROBAR_COMISION: (idComision) =>
		`${BASE}/comision/aprobar/${idComision}`,
}
