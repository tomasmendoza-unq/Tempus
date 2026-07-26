import { createBrowserRouter } from "react-router-dom"
import { authRoutes } from "../feature/auth/routes/auth.routes"
import { alumnoRoutes } from "../feature/alumno/routes/alumno.routes"
import { AuthenticatedLayout } from "./layouts/AuthenticatedLayout"

export const router = createBrowserRouter([
	...authRoutes,

	{ element: <AuthenticatedLayout />, children: alumnoRoutes },
])
