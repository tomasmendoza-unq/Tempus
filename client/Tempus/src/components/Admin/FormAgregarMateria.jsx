import { useCrearMateria, useFormMateria } from "../../hooks/materia"

export default function FormAgregarMateria() {
  const { crearMateria, loading, error } = useCrearMateria()
  const { formMateria, updateFormMateria } = useFormMateria()

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      await crearMateria(formMateria)
      updateFormMateria({ materiaNombre: "", correlativas: [] })
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

  return (
    <form
      className="flex items-center justify-center pt-10"
      onSubmit={handleSubmit}
    >
      <div className="flex items-center justify-center flex-col gap-3.5 bg-white rounded p-6">
        <label className="text-black">Nombre materia:</label>
        <input
          className="border border-gray-300 rounded p-2"
          placeholder="Materia"
          type="text"
          value={formMateria.materiaNombre}
          onChange={handleInputChange}
          disabled={loading}
        />
        {error && <p className="text-red-500 text-sm">{error.message}</p>}
        <button
          className="mt-4 bg-red-950 text-white py-2 px-4 rounded disabled:opacity-50"
          type="submit"
          disabled={loading}
        >
          {loading ? "Cargando..." : "Cargar materia"}
        </button>
      </div>
    </form>
  )
}
