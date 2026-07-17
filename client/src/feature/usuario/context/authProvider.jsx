import { useMemo, useState } from "react"

export const AuthProvider = ({ children }) => {
	const [user, setUser] = useState(null)
	const [token, setToken] = useState(null)
	const [error, setError] = useState(null)
	const [loading, setLoading] = useState(false)

	const login = async (username, password) => {
		setLoading(true)

		setLoading(false)
	}

	const logout = () => {
		setUser(null)
		setToken(null)
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
		}
	}, [user, token, loading, error])

	return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}
