import { useEffect, useMemo, useState } from "react"
import { AuthContext } from "./authContext"
import { login as loginService } from "../service/login.service"
import { register as registerService } from "../service/register.service"
import { getToken } from "../service/token.service"
import { router } from "../../../app/routes"

export const AuthProvider = ({ children }) => {
	const [user, setUser] = useState(null)
	const [token, setToken] = useState(null)
	const [error, setError] = useState(null)
	const [loading, setLoading] = useState(false)

	const login = async (request) => {
		setLoading(true)
		const response = await loginService(request)

		if (!response.ok) {
			setError(response.error)
		}

		setUser(response.data)
		setToken(response.token)

		router.navigate("/perfil")

		setLoading(false)
	}

	const logout = () => {
		setUser(null)
		setToken(null)
	}

	const register = async (request) => {
		setLoading(true)

		const response = await registerService(request)

		if (!response.ok) {
			setError(response.error)
		}
		setUser(response.data)
		setToken(response.token)
		router.navigate("/perfil")

		setLoading(false)
	}

	const value = useMemo(() => {
		return {
			user,
			token,
			loading,
			error,
			isLoggedIn: Boolean(token),
			logout,
			login,
			register,
		}
	}, [user, token, loading, error])

	useEffect(() => {
		const restoreSession = async () => {
			try {
				const stored = await getToken()
				if (stored) {
					const { user, token } = JSON.parse(stored)
					setUser(user)
					setToken(token)
				}
			} catch (error) {
				setError(error.message)
			} finally {
				setLoading(false)
			}
		}
		restoreSession()
	}, [])

	return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}
