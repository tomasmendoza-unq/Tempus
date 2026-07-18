export const ConfirmacionCarga = ({ nombreCarrera, setNombreCarrera, onConfirmar, guardando }) => (
  <div className="bg-white p-4 rounded-xl shadow-md border border-gray-100 flex flex-col md:flex-row gap-4 items-end">
    <div className="flex-1 w-full">
      <label className="text-[10px] font-bold text-gray-400 uppercase ml-1">Nombre de la Carrera</label>
      <input 
        type="text"
        placeholder="Ej: Tecnicatura en Programación Informática"
        className="w-full p-2 border border-gray-200 rounded-lg outline-none focus:ring-2 focus:ring-red-900"
        value={nombreCarrera}
        onChange={(e) => setNombreCarrera(e.target.value)}
      />
    </div>
    <button 
      onClick={onConfirmar}
      disabled={guardando || !nombreCarrera}
      className="bg-green-700 hover:bg-green-800 text-white px-8 py-2 rounded-lg font-bold transition-all disabled:opacity-50 shadow-sm"
    >
      {guardando ? 'Guardando...' : 'Confirmar Carga'}
    </button>
  </div>
);