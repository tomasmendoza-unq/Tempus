import { createBrowserRouter } from "react-router-dom"
import { authRoutes } from "../feature/auth/routes/auth.routes"
import { alumnoRoutes } from "../feature/alumno/routes/alumno.routes"
import { AuthenticatedLayout } from "./layouts/AuthenticatedLayout"
import { NotFound } from "../shared/components/notFound/NotFound"

export const router = createBrowserRouter([
	...authRoutes,

	{ element: <AuthenticatedLayout />, children: alumnoRoutes },
	{ path: "*", element: <NotFound /> },
])
