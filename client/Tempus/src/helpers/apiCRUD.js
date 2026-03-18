import { getErrorMessage } from "./errorMessages"

export const createApi = (baseURL) => {
  const getHeaders = (additionalHeaders = {}) => {
    const token = localStorage.getItem("token");
    const headers = {
      ...(token ? { "Authorization": `Bearer ${token}` } : {}),
      ...additionalHeaders,
    };

    if (!headers["isMultipart"]) {
      headers["Content-Type"] = "application/json";
    } else {

      delete headers["Content-Type"];
      delete headers["isMultipart"];
    }

    return headers;
  };

  const parseResponse = async (res) => {
    const text = await res.text()
    try { return JSON.parse(text) } catch { return text }
  }

  return {
    get: async (endpoint, headers = {}) => {
      const res = await fetch(`${baseURL}${endpoint}`, {
        method: "GET",
        headers: getHeaders(headers),
      })
      if (!res.ok) throw new Error(getErrorMessage(res.status))
      return parseResponse(res)
    },

    post: async (endpoint, data, headers = {}) => {
      const isFormData = data instanceof FormData;

      const res = await fetch(`${baseURL}${endpoint}`, {
        method: "POST",
        headers: getHeaders(isFormData ? { ...headers, isMultipart: true } : headers),
        body: isFormData ? data : JSON.stringify(data),
      })

      if (!res.ok) {
        const errorData = await res.json().catch(() => ({}));
        const errorMessage =
          (errorData?.detalles && Object.values(errorData.detalles)[0]) ||
          errorData?.mensaje ||
          getErrorMessage(res.status)
        throw new Error(errorMessage)
      }
      return parseResponse(res)
    },

    put: async (endpoint, data, headers = {}) => {
      const isFormData = data instanceof FormData;

      const res = await fetch(`${baseURL}${endpoint}`, {
        method: "PUT",
        headers: getHeaders(isFormData ? { ...headers, isMultipart: true } : headers),
        body: isFormData ? data : JSON.stringify(data),
      })
      if (!res.ok) throw new Error(getErrorMessage(res.status))
      return parseResponse(res)
    },

    delete: async (endpoint, headers = {}) => {
      const res = await fetch(`${baseURL}${endpoint}`, {
        method: "DELETE",
        headers: getHeaders(headers),
      })
      if (!res.ok) throw new Error(getErrorMessage(res.status))
      return parseResponse(res)
    },

    patch: async (endpoint, data, headers = {}) => {
      const isFormData = data instanceof FormData;

      const res = await fetch(`${baseURL}${endpoint}`, {
        method: "PATCH",
        headers: getHeaders(isFormData ? { ...headers, isMultipart: true } : headers),
        body: isFormData ? data : JSON.stringify(data),
      })
      if (!res.ok) throw new Error(getErrorMessage(res.status))
      return parseResponse(res)
    },
  }
}