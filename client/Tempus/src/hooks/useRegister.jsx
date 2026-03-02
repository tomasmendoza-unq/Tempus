import { registrarUsuarioService } from "../services/authService"
import { useAuthContext } from "./useAuthContext" // Asumiendo que tenés un AuthContext
import { toast } from "react-toastify"
import { useNavigate } from "react-router-dom"

export function useRegistrarUsuario() {
  const { 
    cargando, 
    fetchAuthRequest, 
    fetchAuthSuccess, 
    fetchAuthFailure 
  } = useAuthContext()
  
  const navigate = useNavigate()

  const registrar = async (usuarioData) => {
    fetchAuthRequest()
    try {
      const data = await registrarUsuarioService(usuarioData)
      
      // Si tu context maneja el estado del usuario creado:
      fetchAuthSuccess(data) 
      
      toast.success("Usuario registrado con éxito. Ya podés iniciar sesión.")
      navigate("/login")
      return data
    } catch (err) {
      fetchAuthFailure(err)
      toast.error(err.response?.data?.message || "Error al registrar el usuario")
      throw err
    }
  }

  return { registrar, cargando }
}