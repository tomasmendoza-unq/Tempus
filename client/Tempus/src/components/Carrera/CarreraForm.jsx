export default function CarreraForm() {
  return (
    <div>
      <form className="flex flex-col p-8 rounded-xl justify-center items-center gap-5 bg-white max-w-2xl shadow-lg border border-gray-100">
        <label
          htmlFor="nombre"
          className="text-sm font-medium text-gray-700 self-start"
        >
          Nombre de la carrera:
        </label>
        <input
          type="text"
          id="nombre"
          name="nombre"
          placeholder="Ej: matematica"
          required
          className="w-full border border-gray-300 rounded-lg p-3 outline-none focus:ring-2 focus:ring-red-900 focus:border-transparent transition-all placeholder:text-gray-400"
        />
        <label
          htmlFor="materias"
          className="text-sm font-medium text-gray-700 self-start"
        >
          ID de las materias:
        </label>
        <input
          placeholder="Ej: 1,2,3"
          type="text"
          id="materias"
          name="materias"
          required
          className="w-full border border-gray-300 rounded-lg p-3 outline-none focus:ring-2 focus:ring-red-900 focus:border-transparent transition-all placeholder:text-gray-400"
        />
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
