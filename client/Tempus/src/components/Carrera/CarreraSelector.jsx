import { useEffect } from "react";
import { useUser } from "../../hooks/useUser"; 
import DropDown from "../Ui/Dropdown/Dropdown";

export default function CarreraSelector() {
  const { perfil, carreraActiva, seleccionarCarrera } = useUser();

  useEffect(() => {
    if (!carreraActiva && perfil?.carreras?.length > 0) {
      const lastId = localStorage.getItem("tempus_carrera_id");
      const guardada = perfil.carreras.find(c => c.idCarrera == lastId);
      seleccionarCarrera(guardada || perfil.carreras[0]);
    }
  }, [perfil, carreraActiva]);

  if (!perfil?.carreras || perfil.carreras.length === 0) return null;

  const handleSeleccion = (seleccion) => {
    const carreraEncontrada = perfil.carreras.find(
      (c) => c.idCarrera === seleccion[1]
    );
    if (carreraEncontrada) {
      seleccionarCarrera(carreraEncontrada);
    }
  };

  return (
    <div className="flex items-center gap-3 bg-red-900/30 px-4 py-2.5 rounded-lg border border-red-800/50 backdrop-blur-sm">
      <div className="flex flex-col items-start">
        <span className="text-[11px] uppercase tracking-tighter text-red-300/80 font-bold leading-none">
          Carrera Activa
        </span>
        <span className="text-base font-medium text-white whitespace-nowrap overflow-hidden text-ellipsis max-w-[220px]">
          {carreraActiva?.nombreCarrera || "Cargando..."}
        </span>
      </div>

      <DropDown
        elements={perfil.carreras}
        tag1="nombreCarrera"
        tag2="idCarrera"
        callback={handleSeleccion}
      />
    </div>
  );
}