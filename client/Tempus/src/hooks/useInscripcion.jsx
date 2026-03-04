import { useState } from "react";
import { anotarseAComisionesService } from "../services/userService";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

export function useInscripcion() {
  const [cargando, setCargando] = useState(false);
  const navigate = useNavigate();

  const inscribirse = async (horarioObjeto) => {
    const comisiones = horarioObjeto?.comisiones;

    if (!comisiones || !Array.isArray(comisiones)) {
      toast.error("No se encontraron comisiones en este horario");
      return;
    }

    const ids = comisiones.map(c => c.comisionId);
    
    setCargando(true);
    try {
      await anotarseAComisionesService(ids);
      toast.success("¡Inscripción realizada con éxito!");
      navigate("/perfil"); 
    } catch (err) {
      const errorMsg = err.response?.data || "Error al procesar la inscripción";
      toast.error(errorMsg);
    } finally {
      setCargando(false);
    }
  };

  return { inscribirse, cargando };
}