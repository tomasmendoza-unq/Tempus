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
      navigate("/horario");
    } catch (err) {
      fetchAuthFailure(err.message || "Error al iniciar sesión"); 
      console.error(err);
      toast.error(err.message || "Error al iniciar sesión");
    }
  };

  const registrar = async (usuarioData) => {
    fetchAuthRequest();
    try {
      await registrarUsuarioService(usuarioData);
      

      fetchAuthSuccess(null); 
      
      toast.success("Registro exitoso, ahora podés loguearte");
      navigate("/login");
    } catch (err) {
      fetchAuthFailure(err.message || "Error en el registro");
      console.error(err);
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