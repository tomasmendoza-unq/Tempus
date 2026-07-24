import axios from "axios"
import { API } from "../../../constants"
import { getErrorMessage } from "../../helpers/errorMessages"
import { getToken } from "../../feature/auth/service/token.service"

export const createApi = (baseURL) => {
	const instance = axios.create({ baseURL })

	instance.interceptors.request.use((config) => {
		const token = getToken()
		if (token) config.headers.Authorization = `Bearer ${token}`
		return config
	})

	instance.interceptors.response.use(
		(response) => response.data,
		(error) => {
			const errorData = error.response?.data
			const errorMessage =
				(errorData?.detalles && Object.values(errorData.detalles)[0]) ||
				errorData?.message ||
				getErrorMessage(error.response?.status)
			return Promise.reject(new Error(errorMessage))
		}
	)

	return {
		get: (endpoint, headers = {}) => instance.get(endpoint, { headers }),
		post: (endpoint, data, headers = {}) =>
			instance.post(endpoint, data, { headers }),
		put: (endpoint, data, headers = {}) =>
			instance.put(endpoint, data, { headers }),
		delete: (endpoint, headers = {}) => instance.delete(endpoint, { headers }),
		patch: (endpoint, data, headers = {}) =>
			instance.patch(endpoint, data, { headers }),
	}
}

export const apiClient = createApi(API.BASE_URL)
