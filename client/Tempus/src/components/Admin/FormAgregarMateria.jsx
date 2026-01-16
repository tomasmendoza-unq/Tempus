import { useCrearMateria, useFormMateria } from "../../hooks/useMateria"

export default function FormAgregarMateria() {
  const { crearMateria, cargando } = useCrearMateria()
  const { formMateria, updateFormMateria } = useFormMateria()

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      const correlativasCadena = formMateria.correlativas || ""
      const correlativasObjetos = correlativasCadena.trim()
        ? correlativasCadena.split(",").map((c) => ({
            materiaNombre: c.trim(),
            correlativas: [],
          }))
        : []

      const payload = {
        ...formMateria,
        correlativas: correlativasObjetos,
      }

      await crearMateria(payload)

      updateFormMateria("materiaNombre", "")
      updateFormMateria("correlativas", "")
    } catch (err) {
      console.error("Error al crear materia:", err)
    }
  }

  const handleInputChange = (e) => {
    const valor = e.target.value
    updateFormMateria("materiaNombre", valor)
  }

  const handleCorrelativasChange = (e) => {
    const valor = e.target.value
    updateFormMateria("correlativas", valor)
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
          disabled={cargando}
        />
        <div className="w-full">
          <label className="block text-sm text-gray-600 mb-1">
            Correlativas (separadas por comas)
          </label>
          <input
            className="w-full border border-gray-300 rounded p-2"
            placeholder="Ej: Matemática, Física, Química"
            type="text"
            value={formMateria.correlativas}
            onChange={handleCorrelativasChange}
            disabled={cargando}
          />
          <p className="text-xs text-gray-500 mt-1">
            Ingresa los nombres de las materias separadas por comas
          </p>
        </div>

        <button
          className="mt-4 bg-red-950 text-white py-2 px-4 rounded disabled:opacity-50"
          type="submit"
          disabled={cargando || !formMateria.materiaNombre.trim()}
        >
          {cargando ? "Cargando..." : "Cargar materia"}
        </button>
      </div>
    </form>
  )
}
