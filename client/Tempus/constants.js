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
  COMISIONES: "/comisiones",
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
  ANOTARSE_A_COMISIONES: (comisionesId) =>
    `/api/usuario/anotarse/${comisionesId.join(",")}`,
  TRAER_MATERIAS_DISPONIBLES: "/api/materia/disponible",
  APROBAR_CURSADA: (comisionId) => `/api/usuario/aprobar/${comisionId}`,
  DESAPROBAR_MATERIA: (materiaId) => `/api/usuario/desaprobar/${materiaId}`,
  OBTENER_COMISION: (comisionId) => `/comision/${comisionId}`,
  CREAR_COMISION: "/comision/crear",
}
