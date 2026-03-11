export const API = {
  BASE_URL: import.meta.env.VITE_API_URL || "http://localhost:8080",
}

export const ROUTES = {
  HOME: "/",
  MATERIAS: "/materias",
  HORARIOS: "/horario",
  REGISTER: "/register",
  LOGIN: "/login",
  CARRERAS: "/carreras",
  PERFIL: "/perfil",
  COMISION_AGREGAR: "/comisiones/agregar",
  COMISION_MOSTRAR: "/comisiones/mostrar",
  COMISION_EDITAR: "/comisiones/editar/:id",
  COMISION_ELIMINAR: "/comisiones/eliminar/:id",
}

export const API_ROUTES = {
  REGISTER: "/auth/register",
  LOGIN: "/auth/login",
  TRAER_PERFIL: "/api/usuario",
  GENERAR_HORARIO_COMPATIBLE: "/api/horario/compatible",
  CREAR_MATERIA: "/api/materia/crear",
  TRAER_MATERIA: (materiaId) => `/api/materia/${materiaId}`,
  TRAER_TODAS_MATERIAS: "/api/materia",
  BUSCAR_MATERIA_POR_NOMBRE: (materiaNombre) =>
    `/api/materia/buscar/${materiaNombre}`,
  TRAER_MATERIAS_DISPONIBLES: "/api/materia/disponible",
  APROBAR_CURSADA: (comisionId) => `/api/usuario/aprobar/${comisionId}`,
  DESAPROBAR_MATERIA: (materiaId) => `/api/usuario/desaprobar/${materiaId}`,
  ASOCIAR_MATERIAS: "/api/materia/asociar",
  CREAR_CARRERA: "/carrera/crear",
  TRAER_CARRERAS_DISPONIBLES: "/carrera/disponibles",
  SUSCRIBIR_CARRERA: (carreraId) =>
    `/api/usuario/suscribir/carrera/${carreraId}`,
  SELECCIONAR_CARRERA_ACTIVA: (carreraId) =>
    `/api/usuario/carreras/${carreraId}/activar`,
  RECUPERAR_CARRERAS: `/carrera/public`,
  ANOTARSE_A_COMISIONES: (comisionesId) =>
    `/api/usuario/anotarse/${comisionesId.join(",")}`,
  CREAR_COMISION: "/comision/crear",
  OBTENER_COMISION: (comisionId) => `/comision/${comisionId}`,
  OBTENER_DATOS_BASICOS: () => "/api/usuario/perfil",
  OBTENER_TODAS_COMISIONES: "/comision",
}
