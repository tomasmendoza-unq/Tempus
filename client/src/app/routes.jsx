import { createBrowserRouter } from "react-router-dom"
import { authRoutes } from "../feature/auth/routes/auth.routes"
import { userRoutes } from "../feature/user/routes/user.routes"
import { AuthenticatedLayout } from "./layouts/AuthenticatedLayout"

export const router = createBrowserRouter([
	...authRoutes,

	{ element: <AuthenticatedLayout />, children: userRoutes },
])
