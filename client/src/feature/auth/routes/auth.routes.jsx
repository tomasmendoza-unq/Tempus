import Login from "../pages/login/Login"
import Register from "../pages/register/Register"

export const authRoutes = [
	{ path: "/login", element: <Login /> },
	{ path: "/register", element: <Register /> },
]
