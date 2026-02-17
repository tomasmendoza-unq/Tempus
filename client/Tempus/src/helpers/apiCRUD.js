import { getErrorMessage } from "./errorMessages"

export const createApi = (baseURL) => ({
  get: async (endpoint, headers = {}) => {
    const res = await fetch(`${baseURL}${endpoint}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        ...headers,
      },
    })

    if (!res.ok) {
      throw new Error(getErrorMessage(res.status))
    }

    return res.json()
  },

  post: async (endpoint, data, headers = {}) => {
    const res = await fetch(`${baseURL}${endpoint}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        ...headers,
      },
      body: JSON.stringify(data),
    })

    if (!res.ok) {
      const errorData = await res.json()
      const errorMessage =
        (errorData?.detalles && Object.values(errorData.detalles)[0]) ||
        errorData?.mensaje ||
        getErrorMessage(res.status)
      throw new Error(errorMessage)
    }

    const text = await res.text()
    try {
      return JSON.parse(text)
    } catch {
      return text
    }
  },

  put: async (endpoint, data, headers = {}) => {
    const res = await fetch(`${baseURL}${endpoint}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        ...headers,
      },
      body: JSON.stringify(data),
    })

    if (!res.ok) {
      throw new Error(getErrorMessage(res.status))
    }

    return res.json()
  },

  delete: async (endpoint, headers = {}) => {
    const res = await fetch(`${baseURL}${endpoint}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        ...headers,
      },
    })

    if (!res.ok) {
      throw new Error(getErrorMessage(res.status))
    }

    const text = await res.text()
    try {
      return JSON.parse(text)
    } catch {
      return text
    }
  },
  patch: async (endpoint, data, headers = {}) => {
    const res = await fetch(`${baseURL}${endpoint}`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
        ...headers,
      },
      body: JSON.stringify(data),
    })
    if (!res.ok) {
      throw new Error(getErrorMessage(res.status))
    }
    return res.json()
  },
})
