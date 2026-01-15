import { useState } from "react"
import { useCrearMateria, useFormMateria } from "../../hooks/useMateria"

export default function FormAgregarMateria() {
  const { crearMateria, loading } = useCrearMateria()
  const { formMateria, updateFormMateria } = useFormMateria()

  const [correlativasTexto, setCorrelativasTexto] = useState("")

  const handleSubmit = (e) => {
    e.preventDefault()
    try {
      const correlativasObjetos = correlativasTexto
        ? correlativasTexto.split(",").map((c) => ({
            materiaNombre: c.trim(),
            correlativas: [],
          }))
        : []

      crearMateria({
        ...formMateria,
        correlativas: correlativasObjetos,
      })

      updateFormMateria({ materiaNombre: "", correlativas: [] })
      setCorrelativasTexto("")
    } catch (err) {
      console.error("Error al crear materia:", err)
    }
  }

  const handleInputChange = (e) => {
    const valor = e.target.value
    updateFormMateria({
      materiaNombre: valor,
      correlativas: formMateria.correlativas,
    })
  }

  const handleCorrelativasChange = (e) => {
    setCorrelativasTexto(e.target.value)
  }

  return (
    <form
      className="flex items-center justify-center pt-10"
      onSubmit={handleSubmit}
    >
      <div className="flex items-center justify-center flex-col gap-3.5 bg-white rounded p-6">
        <input
          className="border border-gray-300 rounded p-2"
          placeholder="Materia"
          type="text"
          value={formMateria.materiaNombre}
          onChange={handleInputChange}
          disabled={loading}
        />
        <div className="w-full">
          <label className="block text-sm text-gray-600 mb-1">
            Correlativas (separadas por comas)
          </label>
          <input
            className="w-full border border-gray-300 rounded p-2"
            placeholder="Ej: Matemática, Física, Química"
            type="text"
            value={correlativasTexto}
            onChange={handleCorrelativasChange}
            disabled={loading}
          />
          <p className="text-xs text-gray-500 mt-1">
            Ingresa los nombres de las materias separadas por comas
          </p>
        </div>

        <button
          className="mt-4 bg-red-950 text-white py-2 px-4 rounded disabled:opacity-50"
          type="submit"
          disabled={loading || !formMateria.materiaNombre.trim()}
        >
          {loading ? "Cargando..." : "Cargar materia"}
        </button>
      </div>
    </form>
  )
}
