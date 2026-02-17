export const getErrorMessage = (status) => {
  const errorMessages = {
    400: "Solicitud inválida. Por favor, verifica los datos ingresados.",
    401: "No autorizado. Por favor, inicia sesión.",
    403: "No tienes permisos para realizar esta acción.",
    404: "Recurso no encontrado.",
    409: "Conflicto. El recurso ya existe.",
    422: "Datos no procesables. Verifica la información ingresada.",
    500: "Error del servidor. Por favor, intenta más tarde.",
    502: "Error de conexión con el servidor.",
    503: "Servicio no disponible. Por favor, intenta más tarde.",
  }

  return (
    errorMessages[status] ||
    "Ha ocurrido un error inesperado. Por favor, intenta nuevamente."
  )
}
