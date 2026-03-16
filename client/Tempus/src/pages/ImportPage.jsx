import React, { useState } from 'react';

export default function ImportPage() {
  const [file, setFile] = useState(null);
  const [materias, setMaterias] = useState([]);
  const [cargando, setCargando] = useState(false);
  const [guardando, setGuardando] = useState(false); // Estado para el POST final
  const [filtro, setFiltro] = useState("");
  const [materiaSeleccionada, setMateriaSeleccionada] = useState(null);
  
  // Estado para capturar el nombre de la carrera antes de guardar
  const [nombreCarrera, setNombreCarrera] = useState("");

  const handleUpload = async () => {
    if (!file) return;
    setCargando(true);
    try {
      const formData = new FormData();
      formData.append('pdf', file);
      const response = await fetch('http://localhost:8080/imports/preview', { method: 'POST', body: formData });
      const data = await response.json();
      const content = data.content || [];
      setMaterias(content);
      if (content.length > 0) setMateriaSeleccionada(content[0]);
    } catch (error) {
      console.error("Error:", error);
    } finally {
      setCargando(false);
    }
  };

  const handleConfirmarCarga = async () => {
    if (!nombreCarrera) return alert("Por favor, ingresá el nombre de la carrera");
    setGuardando(true);

    // Mapeamos al formato CarreraDTOBulkRequest del backend
    const bulkRequest = {
      nombreCarrera: nombreCarrera,
      materias: materias.map(m => ({
        materiaNombre: m.materiaNombre,
        comisiones: m.comisiones.map(c => ({
          nombreComision: c.comisionNombre, // Mapeo a nombreComision según tu DTO
          modalidad: c.modalidad,
          claseHorario: c.claseHorario
        }))
      }))
    };

    try {
      const response = await fetch('http://localhost:8080/carrera/load', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(bulkRequest)
      });

      if (response.ok) {
        alert("¡Oferta cargada con éxito!");
        setMaterias([]);
        setNombreCarrera("");
      }
    } catch (error) {
      console.error("Error al guardar:", error);
      alert("Error al guardar la oferta");
    } finally {
      setGuardando(false);
    }
  };

  const materiasFiltradas = materias.filter(m => 
    m.materiaNombre.toLowerCase().includes(filtro.toLowerCase())
  );

  return (
    <div className="min-h-screen p-6 bg-gray-50">
      <div className="max-w-7xl mx-auto space-y-6">
        
        {/* Header y Upload */}
        <div className="bg-white p-6 rounded-xl shadow-md border border-gray-100 flex flex-col md:flex-row justify-between items-center gap-4">
          <div>
            <h1 className="text-2xl font-bold text-red-950">Importación de Oferta</h1>
            <p className="text-sm text-gray-500">Previsualización de materias y comisiones</p>
          </div>
          <div className="flex gap-2 bg-red-50/50 p-2 rounded-xl border border-red-100">
            <input type="file" accept=".pdf" onChange={(e) => setFile(e.target.files[0])} className="text-xs file:bg-red-950 file:text-white file:border-0 file:px-4 file:py-2 file:rounded-lg cursor-pointer" />
            <button onClick={handleUpload} disabled={!file || cargando} className="bg-red-950 text-white px-6 py-2 rounded-lg text-sm font-bold disabled:opacity-50 transition-colors">
              {cargando ? 'Procesando...' : 'Cargar PDF'}
            </button>
          </div>
        </div>

        {/* Panel de confirmación final (solo si hay datos) */}
        {materias.length > 0 && (
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
              onClick={handleConfirmarCarga}
              disabled={guardando || !nombreCarrera}
              className="bg-green-700 hover:bg-green-800 text-white px-8 py-2 rounded-lg font-bold transition-all disabled:opacity-50 shadow-sm"
            >
              {guardando ? 'Guardando...' : 'Confirmar Carga'}
            </button>
          </div>
        )}

        {materias.length > 0 && (
          <div className="flex flex-col md:flex-row gap-6 h-[65vh]">
            
            {/* Columna Izquierda: Selector de Materias */}
            <div className="w-full md:w-1/3 bg-white rounded-xl shadow-md border border-gray-100 flex flex-col overflow-hidden">
              <div className="p-4 border-b border-gray-100 bg-gray-50">
                <input 
                  type="text"
                  placeholder="Buscar materia..."
                  className="w-full p-2 text-sm border border-gray-300 rounded-lg focus:ring-2 focus:ring-red-900 outline-none"
                  onChange={(e) => setFiltro(e.target.value)}
                />
              </div>
              <div className="flex-1 overflow-y-auto">
                {materiasFiltradas.map((m, idx) => (
                  <button
                    key={idx}
                    onClick={() => setMateriaSeleccionada(m)}
                    className={`w-full text-left px-6 py-4 text-sm font-medium border-b border-gray-50 transition-colors ${
                      materiaSeleccionada?.materiaNombre === m.materiaNombre 
                      ? 'bg-red-50 text-red-950 border-r-4 border-r-red-950' 
                      : 'text-gray-600 hover:bg-gray-50'
                    }`}
                  >
                    {m.materiaNombre}
                  </button>
                ))}
              </div>
            </div>

            {/* Columna Derecha: Detalle de Comisiones */}
            <div className="w-full md:w-2/3 bg-white rounded-xl shadow-md border border-gray-100 flex flex-col overflow-hidden">
              {materiaSeleccionada ? (
                <>
                  <div className="bg-red-950 p-4">
                    <h2 className="text-white font-bold uppercase tracking-tight">{materiaSeleccionada.materiaNombre}</h2>
                  </div>
                  <div className="p-6 overflow-y-auto grid grid-cols-1 sm:grid-cols-2 gap-4">
                    {materiaSeleccionada.comisiones.map((com, cIdx) => (
                      <div key={cIdx} className="bg-gray-50 p-4 rounded-xl border border-gray-200 hover:shadow-md transition-shadow">
                        <div className="flex justify-between items-start mb-3">
                          <span className="font-bold text-gray-800">{com.comisionNombre}</span>
                          <span className="text-[10px] px-2 py-0.5 bg-white border border-red-200 text-red-900 rounded-full font-bold uppercase">
                            {com.modalidad}
                          </span>
                        </div>
                        <div className="space-y-2">
                          {com.claseHorario.map((horario, hIdx) => (
                            <div key={hIdx} className="flex items-center text-xs">
                              <span className="w-20 font-bold text-gray-400 uppercase">{horario.dia}</span>
                              <span className="bg-white px-3 py-1 rounded-md border border-gray-100 font-medium text-gray-700">
                                {horario.inicio.substring(0, 5)} - {horario.fin.substring(0, 5)}
                              </span>
                            </div>
                          ))}
                        </div>
                      </div>
                    ))}
                  </div>
                </>
              ) : (
                <div className="flex items-center justify-center h-full text-gray-400 italic">
                  Seleccioná una materia para ver sus detalles
                </div>
              )}
            </div>
          </div>
        )}
      </div>
    </div>
  );
}