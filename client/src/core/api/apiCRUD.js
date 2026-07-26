import axios from "axios"

import {
	attachRequestInterceptor,
	attachResponseInterceptor,
} from "./interceptors/interceptors"

export const API = {
	BASE_URL: import.meta.env.VITE_API_URL || "http://localhost:8080",
}

export const createApi = (baseURL) => {
	const instance = axios.create({ baseURL })

	attachRequestInterceptor(instance)
	attachResponseInterceptor(instance)

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
