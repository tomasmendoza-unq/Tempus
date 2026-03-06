import {
  useBuscarMateriaPorNombre,
  useFormMateria,
} from "../../hooks/useMateria"

export default function BuscadorDeMaterias() {
  const { buscarMateriaPorNombre, cargando } = useBuscarMateriaPorNombre()
  const { updateFormMateria, formMateria } = useFormMateria()

  const handleSubmit = async (e) => {
    e.preventDefault()
    if (formMateria.materiaNombre.trim() !== "") {
      await buscarMateriaPorNombre(formMateria.materiaNombre)
    }
  }

  return (
    <form onSubmit={handleSubmit} className="flex flex-col gap-3">
      <div className="flex flex-col gap-1">
        <label className="text-sm font-medium text-gray-700">
          Nombre de la materia
        </label>
        <input
          className="border border-gray-200 rounded-lg p-2.5 text-sm focus:outline-none focus:ring-2 focus:ring-red-900 focus:border-transparent transition"
          onChange={(e) => {
            updateFormMateria("materiaNombre", e.target.value)
          }}
          value={formMateria.materiaNombre}
          placeholder="Ingresa nombre materia..."
          disabled={cargando}
        />
      </div>
      <button
        className="bg-red-950 hover:bg-red-900 text-white py-2.5 px-4 rounded-lg font-medium transition disabled:opacity-50"
        type="submit"
        disabled={cargando || !formMateria.materiaNombre.trim()}
      >
        {cargando ? "Buscando..." : "Buscar materia"}
      </button>
    </form>
  )
}
