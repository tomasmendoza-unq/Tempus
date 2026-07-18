export const COMISION_ENDPOINTS = {
  GET_COMISION: "/comision",
  CREATE_COMISION: "/comision/crear",
  GET_ALL_COMISIONES: (page = 0) => `/comision?page=${page}`,
  UPDATE_COMISION: (comisionId) => `/comision/${comisionId}`,
  DELETE_COMISION: (comisionId) => `/comision/${comisionId}`,
}
