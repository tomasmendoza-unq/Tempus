import { createBrowserRouter } from "react-router-dom"
import { authRoutes } from "../feature/auth/routes/auth.routes"
import { perfilRoutes } from "../feature/perfil/routes/perfil.routes"

export const router = createBrowserRouter([...authRoutes, ...perfilRoutes])
