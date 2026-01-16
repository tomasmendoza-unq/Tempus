export const crearMateriaService = async (formData) => {
  const response = await fetch("/api/materia/crear", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(formData),
  })

  const text = await response.text()

  if (!response.ok) {
    throw new Error(`Error ${response.status}: ${text}`)
  }
}

export const traerMateriaService = async (idMateria) => {
  const response = await fetch(`api/materia/${idMateria}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
  return response.json()
}

export const traerTodasMateriasService = async () => {
  const response = await fetch(`api/materia/`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
  return response.json()
}

export const buscarMateriaPorNombreService = async (materiaNombre) => {
  const response = await fetch(`api/materia/buscar/${materiaNombre}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
  return response.json()
}
