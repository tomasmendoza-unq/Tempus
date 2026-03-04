import { loginService, registrarUsuarioService } from "../services/authService";
import { useAuthContext } from "../contexts/AuthContext";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export function useAuth() {
  const { 
    usuario,
    isAuthenticated,
    cargando, 
    fetchAuthRequest, 
    fetchAuthSuccess, 
    fetchAuthFailure,
    logout 
  } = useAuthContext();
  
  const navigate = useNavigate();

  const login = async (loginData) => {
    fetchAuthRequest();
    try {
      const data = await loginService(loginData);
      localStorage.setItem("token", data.token);
      
      fetchAuthSuccess(data.usuario); 
      
      toast.success("¡Bienvenido!");
      navigate("/perfil");
    } catch (err) {
      fetchAuthFailure(err.message || "Error al iniciar sesión"); 
      toast.error(err.message || "Error al iniciar sesión");
    }
  };

  const registrar = async (usuarioData) => {
    fetchAuthRequest();
    try {

      const data = await registrarUsuarioService(usuarioData);
      

      localStorage.setItem("token", data.token);
      
      fetchAuthSuccess(data.usuario); 
      
      toast.success("¡Cuenta creada y sesión iniciada!");
      navigate("/perfil");
    } catch (err) {
      fetchAuthFailure(err.message || "Error en el registro");
      toast.error(err.message || "Error en el registro");
    }
  };

  return { login, registrar, logout, usuario, isAuthenticated, cargando };
}


export const useLogin = () => {
    const { login, cargando } = useAuth();
    return { login, cargando };
};

export const useRegistrarUsuario = () => {
    const { registrar, cargando } = useAuth();
    return { registrar, cargando };
};