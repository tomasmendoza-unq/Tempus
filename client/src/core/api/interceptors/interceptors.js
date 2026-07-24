import { getToken } from "../../../feature/auth/service/token.service"

export const attachRequestInterceptor = (instance) => {
	instance.interceptors.request.use((config) => {
		const token = getToken()
		if (token) config.headers.Authorization = `Bearer ${token}`
		return config
	})
}

export const attachResponseInterceptor = (instance) => {
	instance.interceptors.response.use(
		(response) => response.data,
		(error) => {
			console.error(
				"[API Error]",
				error.response?.status,
				error.config?.url,
				error.response?.data
			)

			if (!error.response) {
				return Promise.reject(new Error("No se pudo conectar con el servidor"))
			}

			const errorData = error.response.data
			const errorMessage =
				(errorData?.detalles && Object.values(errorData.detalles)[0]) ||
				errorData?.message

			const err = new Error(errorMessage)
			err.status = error.response.status
			err.data = errorData

			return Promise.reject(err)
		}
	)
}
