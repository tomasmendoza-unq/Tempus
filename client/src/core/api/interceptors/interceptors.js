import { getToken } from "../../../feature/auth/service/token.service"

export const attachRequestInterceptor = (instance) => {
	instance.interceptors.request.use(
		(config) => {
			const token = getToken()

			if (token) {
				config.headers.Authorization = `Bearer ${token}`
			}

			return config
		},
		(error) => Promise.reject(error)
	)
}

export const attachResponseInterceptor = (instance) => {
	instance.interceptors.response.use(
		(response) => ({ ok: true, data: response.data }),

		(error) => {
			console.error(
				"[API Error]",
				error.response?.status,
				error.config?.url,
				error.response?.data
			)

			if (!error.response) {
				return { ok: false, error: "No se pudo conectar con el servidor" }
			}

			const { data, status } = error.response

			const message =
				Object.values(data?.detalles ?? {})[0] ??
				data?.message ??
				"Ocurrió un error inesperado"

			return { ok: false, error: message, status, data }
		}
	)
}
