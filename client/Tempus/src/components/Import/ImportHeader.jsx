export const ImportHeader = ({ onUpload, cargando, setFile }) => (
  <div className="bg-white p-6 rounded-xl shadow-md border border-gray-100 flex flex-col md:flex-row justify-between items-center gap-4">
    <div>
      <h1 className="text-2xl font-bold text-red-950">Importación de Oferta</h1>
      <p className="text-sm text-gray-500">Previsualización de materias y comisiones</p>
    </div>
    <div className="flex gap-2 bg-red-50/50 p-2 rounded-xl border border-red-100">
      <input 
        type="file" 
        accept=".pdf" 
        onChange={(e) => setFile(e.target.files[0])} 
        className="text-xs file:bg-red-950 file:text-white file:border-0 file:px-4 file:py-2 file:rounded-lg cursor-pointer" 
      />
      <button 
        onClick={onUpload} 
        disabled={cargando} 
        className="bg-red-950 text-white px-6 py-2 rounded-lg text-sm font-bold disabled:opacity-50 transition-colors"
      >
        {cargando ? 'Procesando...' : 'Cargar PDF'}
      </button>
    </div>
  </div>
);