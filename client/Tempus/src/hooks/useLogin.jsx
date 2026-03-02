import { loginService } from "../services/authService"
import { useAuthContext } from "./useAuthContext"
import { toast } from "react-toastify"
import { useNavigate } from "react-router-dom"

export function useLogin() {
  const { 
    cargando, 
    fetchAuthRequest, 
    fetchAuthSuccess, 
    fetchAuthFailure 
  } = useAuthContext()
  
  const navigate = useNavigate()

  const login = async (loginData) => {
    fetchAuthRequest()
    try {
      const data = await loginService(loginData)
      
      // data debería ser tu LoginResponseDTO (token + usuario)
      localStorage.setItem("token", data.token)
      
      fetchAuthSuccess(data.usuario)
      
      toast.success("¡Bienvenido!")
      navigate("/horario")
      return data
    } catch (err) {
      fetchAuthFailure(err)
      toast.error(err.response?.data?.message || "Credenciales inválidas")
      throw err
    }
  }

  return { login, cargando }
}