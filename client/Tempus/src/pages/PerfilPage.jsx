import { useEffect } from "react";
import { useUser } from "../hooks/useUser";
import { InfoPersonal } from "../components/Perfil/InfoPersonal";
import { ListaCursadas } from "../components/Perfil/ListaCursadas";
import { ListaFinales } from "../components/Perfil/ListaFinales";
import { SuscripcionCarreras } from "../components/Carrera/SuscripcionCarreras";

export default function PerfilPage() {
  const { 
    perfil, 
    cargando, 
    obtenerPerfil, 
    aprobarCursada, 
    desaprobarMateria,
    suscribirCarrera,           
    carrerasDisponibles,       
    obtenerCarrerasDisponibles  
  } = useUser();

  useEffect(() => {
    obtenerPerfil();
    obtenerCarrerasDisponibles(); 
  }, []);

  if (cargando && !perfil) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <div className="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-red-950"></div>
      </div>
    );
  }

  if (!perfil) return null;

  return (
    <div className="min-h-screen p-6 flex flex-col items-center">
      <div className="bg-white p-8 rounded-xl shadow-lg w-full max-w-4xl border border-gray-100 space-y-8">
        
        <InfoPersonal 
          nombre={perfil.nombre} 
          apellido={perfil.apellido} 
          email={perfil.email} 
        />

        <SuscripcionCarreras 
          carrerasUsuario={perfil.carreras} 
          onSuscribir={suscribirCarrera}      
          carrerasDisponibles={carrerasDisponibles} 
        />

        <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
          <ListaCursadas 
            comisiones={perfil.comisiones} 
            onAprobar={aprobarCursada} 
          />
          
          <ListaFinales 
            materias={perfil.materiaDTOResponseSimples} 
            onDesaprobar={desaprobarMateria}
          />
        </div>
        
      </div>
    </div>
  );
}