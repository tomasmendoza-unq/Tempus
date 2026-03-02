export function GeneradorControls({ cantidad, setCantidad, onCalcular, cargando, disabled }) {
  return (
    <div className="bg-white p-4 rounded-lg shadow border border-gray-200 flex flex-wrap gap-4 items-end justify-center">
      <div>
        <label className="block text-sm font-medium text-gray-700">Cantidad de opciones</label>
        <input 
          type="number" 
          min="1" max="10"
          className="mt-1 block w-24 border-gray-300 rounded-md shadow-sm border p-2"
          value={cantidad}
          onChange={(e) => setCantidad(Number(e.target.value))}
        />
      </div>
      <button
        onClick={onCalcular}
        disabled={cargando || disabled}
        className="bg-red-950 text-white px-8 py-2 rounded-md hover:bg-red-900 transition-all disabled:bg-gray-400 shadow-md"
      >
        {cargando ? "Calculando..." : "Generar Combinaciones"}
      </button>
    </div>
  );
}