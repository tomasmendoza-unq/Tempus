import LoginPage from "../pages/login/LoginPage"
import RegisterPage from "../pages/register/RegisterPage"

export const authRoutes = [
	{ path: "/login", element: <LoginPage /> },
	{ path: "/register", element: <RegisterPage /> },
]
