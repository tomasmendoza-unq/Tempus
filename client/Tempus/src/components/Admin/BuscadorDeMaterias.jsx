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
    <form onSubmit={handleSubmit} className="flex-1 flex flex-col gap-3.5">
      <input
        className="border border-gray-300 rounded p-2"
        onChange={(e) => {
          updateFormMateria("materiaNombre", e.target.value)
        }}
        value={formMateria.materiaNombre}
        placeholder="Ingresa nombre materia..."
        disabled={cargando}
      />
      <button
        className="h-10 bg-red-950 text-white py-2 px-4 rounded disabled:opacity-50 flex-shrink-0"
        type="submit"
        disabled={cargando || !formMateria.materiaNombre.trim()}
      >
        {cargando ? "Buscando..." : "Buscar Materia"}
      </button>
    </form>
  )
}
