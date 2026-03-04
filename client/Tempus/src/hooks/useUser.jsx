import { traerPerfilService, aprobarCursadaService } from "../services/userService";
import { useUserContext } from "../contexts/UserContext";
import { toast } from "react-toastify";

export function useUser() {
  const { 
    perfil, 
    cargando, 
    fetchUserRequest, 
    fetchUserSuccess, 
    fetchUserFailure 
  } = useUserContext();

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

  return { perfil, cargando, obtenerPerfil, aprobarCursada };
}