import { createBrowserRouter } from "react-router-dom"
import { authRoutes } from "../feature/auth/routes/auth.routes"
import { userRoutes } from "../feature/user/routes/user.routes"

export const router = createBrowserRouter([...authRoutes, ...userRoutes])
