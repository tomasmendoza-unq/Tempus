import { createBrowserRouter } from "react-router-dom"
import { authRoutes } from "../feature/auth/routes/auth.routes"

export const router = createBrowserRouter([...authRoutes])
