import { traerPerfilService } from "../services/userService";
import { useUserContext } from "../contexts/UserContext";
import { toast } from "react-toastify";

export function useUser() {
  const { perfil, cargando, fetchUserRequest, fetchUserSuccess, fetchUserFailure } = useUserContext();

  const obtenerPerfil = async () => {
    fetchUserRequest();
    try {
      const data = await traerPerfilService();
      fetchUserSuccess(data);
    } catch (err) {
      fetchUserFailure(err.message || "Error al obtener perfil");
      console.log(err)
      toast.error("No se pudo cargar la información del alumno");
    }
  };

  return { perfil, cargando, obtenerPerfil };
}