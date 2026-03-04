import { useEffect } from "react";
import { useUser } from "../hooks/useUser";

export default function PerfilPage() {
  const { perfil, cargando, obtenerPerfil } = useUser();

  useEffect(() => {
    obtenerPerfil();
  }, []);

  if (cargando) return <div className="text-white text-center mt-10">Cargando...</div>;
  if (!perfil) return null;

  return (
    <div className="min-h-screen p-6 flex flex-col items-center">
      <div className="bg-white p-8 rounded-xl shadow-lg w-full max-w-4xl border border-gray-100">
        <h2 className="text-3xl font-extrabold text-red-950 mb-6 italic">Detalle del Alumno</h2>
        
        <div className="grid grid-cols-2 gap-4 mb-8">
          <p><strong>Nombre:</strong> {perfil.nombre} {perfil.apellido}</p>
          <p><strong>Email:</strong> {perfil.email}</p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <section>
            <h3 className="text-xl font-bold text-red-900 mb-2">Comisiones</h3>
            <div className="bg-gray-50 p-4 rounded-lg border">
              {perfil.comisiones?.map(c => <div key={c.id} className="text-gray-700 py-1 border-b last:border-0">{c.nombre}</div>)}
            </div>
          </section>
          
          <section>
            <h3 className="text-xl font-bold text-red-900 mb-2">Materias Aprobadas</h3>
            <div className="bg-gray-50 p-4 rounded-lg border">
              {perfil.materiaDTOResponseSimples?.map(m => <div key={m.id} className="text-gray-700 py-1 border-b last:border-0">{m.nombre}</div>)}
            </div>
          </section>
        </div>
      </div>
    </div>
  );
}