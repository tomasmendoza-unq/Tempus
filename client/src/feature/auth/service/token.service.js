export const setToken = (data) => {
	localStorage.setItem("auth", JSON.stringify(data))
}

export const getStoredAuth = () => {
	const stored = localStorage.getItem("auth")
	return stored ? JSON.parse(stored) : null
}

export const getToken = () => {
	const auth = getStoredAuth()
	return auth?.token ?? null
}

export const removeToken = () => {
	localStorage.removeItem("auth")
}
