export const MATERIA_ENDPOINTS = {
	GET_ALL_MATERIAS: () => `/materia`,
	GET_DISPONIBLES_MATERIAS: () => `/materia/disponible`,
	GET_MATERIA_BY_NAME: (name) => `/materia/buscar/${encodeURIComponent(name)}`,
}
