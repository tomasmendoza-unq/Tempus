export const setToken = (data) => {
	localStorage.setItem("auth", JSON.stringify(data))
}

export const getToken = () => {
	return localStorage.getItem("auth")
}

export const removeToken = () => {
	localStorage.removeItem("auth")
}
