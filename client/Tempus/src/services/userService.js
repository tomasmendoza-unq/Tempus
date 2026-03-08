import { API, API_ROUTES } from "../../constants"
import { createApi } from "../helpers/apiCRUD"

const api = createApi(API.BASE_URL)

export const traerPerfilService = () =>
    api
        .get(API_ROUTES.TRAER_PERFIL)
        .then((res) => res)
        .catch((error) => {
            throw error
    })

export const anotarseAComisionesService = (comisionesId) =>
  api
    .post(API_ROUTES.ANOTARSE_A_COMISIONES(comisionesId))
    .then((res) => res)
    .catch((error) => {
      throw error
    })

export const aprobarCursadaService = (comisionId) =>
  api
    .post(API_ROUTES.APROBAR_CURSADA(comisionId))
    .then((res) => res)
    .catch((error) => {
      throw error;
    });

export const desaprobarMateriaService = (materiaId) =>
  api
    .post(API_ROUTES.DESAPROBAR_MATERIA(materiaId))
    .then((res) => res)
    .catch((error) => {
      throw error;
    });

export const obtenerDatosBasicos = () =>
  api
    .get(API_ROUTES.OBTENER_DATOS_BASICOS())
    .then((res) => res)
    .catch((error) => {
      throw error;
    });

export const obtenerCarrerasDisponiblesService = () =>
  api
    .get(API_ROUTES.CARRERAS_DISPONIBLES)
    .then((res) => res)
    .catch((error) => {
      throw error;
    });

export const suscribirCarreraService = (carreraId) =>
  api
    .post(API_ROUTES.SUSCRIBIR_CARRERA(carreraId))
    .then((res) => res)
    .catch((error) => {
      throw error;
    });