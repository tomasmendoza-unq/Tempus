export const MATERIA_ENDPOINTS = {
	GET_ALL_MATERIAS: () => `/materia`,
	GET_DISPONIBLES_MATERIAS: () => `/api/materia/disponible`,
	GET_MATERIA_BY_NAME: (name) =>
		`api/materia/buscar/${encodeURIComponent(name)}`,
}
