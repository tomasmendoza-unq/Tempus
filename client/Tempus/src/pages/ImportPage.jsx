import React, { useState } from 'react';
import { ImportHeader } from '../components/Import/ImportHeader';
import { ConfirmacionCarga } from '../components/Import/ConfirmacionCarga';
import { DetalleMateria } from '../components/Import/DetalleMateria';
import useCarrera from '../hooks/useCarrera'; // Importamos tu hook

export default function ImportPage() {
  const [file, setFile] = useState(null);
  const [materias, setMaterias] = useState([]);
  const [cargando, setCargando] = useState(false);
  const [guardando, setGuardando] = useState(false); 
  const [filtro, setFiltro] = useState("");
  const [materiaSeleccionada, setMateriaSeleccionada] = useState(null);
  const [nombreCarrera, setNombreCarrera] = useState("");

  const { crearCarreraBulk } = useCarrera(); // Extraemos la función del hook

  const handleUpload = async () => {
    if (!file) return;
    setCargando(true);
    try {
      const formData = new FormData();
      formData.append('pdf', file);
      // Aquí podrías también tener un service para el preview si querés refactorizar más
      const response = await fetch('http://localhost:8080/imports/preview', { method: 'POST', body: formData });
      const data = await response.json();
      const content = data.content || [];
      setMaterias(content);
      if (content.length > 0) setMateriaSeleccionada(content[0]);
    } catch (error) {
      console.error("Error en preview:", error);
    } finally {
      setCargando(false);
    }
  };

  const handleConfirmarCarga = async () => {
    setGuardando(true);
    
    const bulkRequest = {
      nombreCarrera,
      materias: materias.map(m => ({
        materiaNombre: m.materiaNombre,
        comisiones: m.comisiones.map(c => ({
          nombreComision: c.comisionNombre,
          modalidad: c.modalidad,
          claseHorario: c.claseHorario
        }))
      }))
    };

    try {
      await crearCarreraBulk(bulkRequest);

      setMaterias([]);
      setNombreCarrera("");
      setFile(null);
    } catch (error) {
    } finally {
      setGuardando(false);
    }
  };

  const materiasFiltradas = materias.filter(m => 
    m.materiaNombre.toLowerCase().includes(filtro.toLowerCase())
  );

  return (
    <div className="min-h-screen p-6 bg-gray-50 space-y-6">
      <div className="max-w-7xl mx-auto space-y-6">
        
        <ImportHeader onUpload={handleUpload} cargando={cargando} setFile={setFile} />

        {materias.length > 0 && (
          <>
            <ConfirmacionCarga 
              nombreCarrera={nombreCarrera} 
              setNombreCarrera={setNombreCarrera} 
              onConfirmar={handleConfirmarCarga} 
              guardando={guardando} 
            />

            <div className="flex flex-col md:flex-row gap-6 h-[65vh]">
              {/* Listado lateral de materias */}
              <div className="w-full md:w-1/3 bg-white rounded-xl shadow-md border border-gray-100 flex flex-col overflow-hidden">
                <div className="p-4 border-b border-gray-100 bg-gray-50">
                  <input 
                    type="text" placeholder="Buscar materia..."
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

              {/* Detalle de la materia seleccionada */}
              <div className="w-full md:w-2/3 bg-white rounded-xl shadow-md border border-gray-100 flex flex-col overflow-hidden">
                <DetalleMateria materia={materiaSeleccionada} />
              </div>
            </div>
          </>
        )}
      </div>
    </div>
  );
}