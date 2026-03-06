import { useCrearMateria, useFormMateria } from "../../hooks/useMateria"
import Titulo from "../Ui/Titulo/Titulo"

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
    <div className="flex flex-col items-center w-full">
      <Titulo texto="Agregar materias" />
      <form
        className="flex items-center justify-center"
        onSubmit={handleSubmit}
      >
        <div className="bg-white rounded-2xl shadow-md border border-gray-100 p-8 w-full max-w-md flex flex-col gap-5">
          <div className="flex flex-col gap-1">
            <label className="text-sm font-medium text-gray-700">
              Nombre de la materia
            </label>
            <input
              className="border border-gray-200 rounded-lg p-2.5 text-sm focus:outline-none focus:ring-2 focus:ring-red-900 focus:border-transparent transition"
              placeholder="Ej: Matemática I"
              type="text"
              value={formMateria.materiaNombre}
              onChange={handleInputChange}
              disabled={cargando}
            />
          </div>
          <div className="flex flex-col gap-1">
            <label className="text-sm font-medium text-gray-700">
              Correlativas
            </label>
            <input
              className="border border-gray-200 rounded-lg p-2.5 text-sm focus:outline-none focus:ring-2 focus:ring-red-900 focus:border-transparent transition"
              placeholder="Ej: Matemática, Física, Química"
              type="text"
              value={formMateria.correlativas}
              onChange={handleCorrelativasChange}
              disabled={cargando}
            />
            <p className="text-xs text-gray-400 mt-0.5">Separalas por comas</p>
          </div>

          <button
            className="mt-2 bg-red-950 hover:bg-red-900 text-white py-2.5 px-4 rounded-lg font-medium transition disabled:opacity-50"
            type="submit"
            disabled={cargando || !formMateria.materiaNombre.trim()}
          >
            {cargando ? "Cargando..." : "Cargar materia"}
          </button>
        </div>
      </form>
    </div>
  )
}
