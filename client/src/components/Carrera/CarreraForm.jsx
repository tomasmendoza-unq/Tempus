import { useState } from "react"
import useCarrera from "../../hooks/useCarrera"
import { useMateriaContext } from "../../contexts/MateriaContext"

export default function CarreraForm() {
  const { crearCarrera } = useCarrera()
  const { materiasSeleccionadas } = useMateriaContext()

  const [formData, setFormData] = useState({
    nombreCarrera: "",
    idsMaterias: [],
  })

  const handleSubmit = async (e) => {
    e.preventDefault()
    const ids = materiasSeleccionadas.map((m) => m.materiaId)
    await crearCarrera({
      nombreCarrera: formData.nombreCarrera,
      idsMaterias: ids,
    })
  }

  return (
    <div>
      <form
        onSubmit={handleSubmit}
        className="flex flex-col p-8 rounded-xl justify-center items-center gap-5 bg-white max-w-2xl shadow-lg border border-gray-100"
      >
        <label
          htmlFor="nombre"
          className="text-sm font-medium text-gray-700 self-start"
        >
          Nombre de la carrera:
        </label>
        <input
          type="text"
          id="nombreCarrera"
          onChange={(e) => {
            setFormData((prev) => ({ ...prev, nombreCarrera: e.target.value }))
          }}
          name="nombreCarrera"
          placeholder="Ej: Licenciatura en Informatica"
          required
          className="w-full border border-gray-300 rounded-lg p-3 outline-none focus:ring-2 focus:ring-red-900 focus:border-transparent transition-all placeholder:text-gray-400"
        />
        <label
          htmlFor="materias"
          className="text-sm font-medium text-gray-700 self-start"
        >
          ID de las materias:
        </label>

        <button
          type="submit"
          className="w-full mt-2 bg-red-950 text-white py-3 rounded-lg font-bold hover:bg-red-900 active:scale-[0.98] shadow-md transition-all"
        >
          Crear Carrera
        </button>
      </form>
    </div>
  )
}
