import { traerPerfilService, aprobarCursadaService, desaprobarMateriaService, obtenerDatosBasicos } from "../services/userService";
import { useUserContext } from "../contexts/UserContext";
import { toast } from "react-toastify";

export function useUser() {
  const { 
    perfil, 
    carreraActiva,   
    cargando, 
    fetchUserRequest, 
    fetchUserSuccess, 
    fetchUserFailure,
    setCarreraActiva  
  } = useUserContext();


  const seleccionarCarrera = (carrera) => {
    setCarreraActiva(carrera);
    localStorage.setItem("tempus_carrera_id", carrera.idCarrera);
  };

  const obtenerPerfil = async () => {
    fetchUserRequest();
    try {
      const data = await traerPerfilService();
      fetchUserSuccess(data);
    } catch (err) {
      fetchUserFailure(err.error || "Error al obtener perfil");
      toast.error("No se pudo cargar la información del alumno");
    }
  };

  const cargarDatosBasicos = async () => {
    fetchUserRequest();
    try {
      const data = await obtenerDatosBasicos();
      fetchUserSuccess(data);

      if (data.carreras?.length > 0 && !carreraActiva) {
        const lastId = localStorage.getItem("tempus_carrera_id");
        const guardada = data.carreras.find(c => c.idCarrera == lastId);
        setCarreraActiva(guardada || data.carreras[0]);
      }
    } catch (err) {
      fetchUserFailure(err.message || "Error al traer los datos basicos del usuario");
    }
  };

  const aprobarCursada = async (comisionId) => {
    fetchUserRequest(); 
    try {
      await aprobarCursadaService(comisionId);
      toast.success("¡Materia aprobada!");
      await obtenerPerfil(); 
    } catch (err) {
      fetchUserFailure(err.message || "Error al aprobar cursada");
      toast.error(err.message || "Error al procesar la materia");
    }
  };

  const desaprobarMateria = async (materiaId) => {
    fetchUserRequest();
    try {
      await desaprobarMateriaService(materiaId);
      toast.success("Materia quitada de aprobadas");
      await obtenerPerfil();
    } catch (err) {
      fetchUserFailure(err.message || "Error al desaprobar materia");
      toast.error("No se pudo realizar la operación");
    }
  };

  return { 
    perfil, 
    carreraActiva,
    cargando, 
    seleccionarCarrera,
    obtenerPerfil, 
    aprobarCursada, 
    desaprobarMateria, 
    cargarDatosBasicos 
  };
}