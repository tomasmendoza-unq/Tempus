export const API = {
  BASE_URL: "http://localhost:8080",
}
export const ROUTES = {
  HOME: "/",
  MATERIAS: "/materias",
}

export const API_ROUTES = {
  CREAR_MATERIA: "/api/materia/crear",
  TRAER_MATERIA: (materiaId) => `/api/materia/${materiaId}`,
  TRAER_TODAS_MATERIAS: "/api/materia",
  BUSCAR_MATERIA_POR_NOMBRE: (materiaNombre) =>
    `/api/materia/buscar/${materiaNombre}`,
}
