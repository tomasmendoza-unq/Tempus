export const API = {
  BASE_URL: "http://localhost:8080",
}
export const ROUTES = {
  HOME: "/",
  MATERIAS: "/materias",
  HORARIOS: "/horario",
  REGISTER: "/register",
  LOGIN: "/login",
  CARRERAS: "/carreras",
  PERFIL: "/perfil",
}

export const API_ROUTES = {
  CREAR_MATERIA: "/api/materia/crear",
  TRAER_MATERIA: (materiaId) => `/api/materia/${materiaId}`,
  TRAER_TODAS_MATERIAS: "/api/materia",
  BUSCAR_MATERIA_POR_NOMBRE: (materiaNombre) =>
    `/api/materia/buscar/${materiaNombre}`,
  GENERAR_HORARIO_COMPATIBLE: "/api/horario/compatible",
  REGISTER: "/auth/register",
  LOGIN: "/auth/login",
  CREAR_CARRERA: "/carrera/crear",
  TRAER_PERFIL: "/api/usuario",
  ANOTARSE_A_COMISIONES: (comisionesId) => `/api/usuario/anotarse/${comisionesId.join(',')}`,
}
