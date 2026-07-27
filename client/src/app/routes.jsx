import { createBrowserRouter } from "react-router-dom"
import { authRoutes } from "../feature/auth/routes/auth.routes"
import { alumnoRoutes } from "../feature/alumno/routes/alumno.routes"
import { AuthenticatedLayout } from "./layouts/AuthenticatedLayout"
import { NotFound } from "../shared/components/notFound/NotFound"
import { horarioRoutes } from "../feature/horario/routes/horario.routes"

export const router = createBrowserRouter([
	...authRoutes,

	{ element: <AuthenticatedLayout />, children: alumnoRoutes },
	{ element: <AuthenticatedLayout />, children: horarioRoutes },
	{ path: "*", element: <NotFound /> },
])
