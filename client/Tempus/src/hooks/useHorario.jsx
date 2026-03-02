import { useHorarioContext } from "../contexts/HorarioContext";
import { generarHorarioCompatibleService } from "../services/horarioService";
import { toast } from "react-toastify";

export function useGenerarHorario() {
  const {
    cargando,
    resultados,
    fetchHorarioRequest,
    fetchHorarioSuccess,
    fetchHorarioFailure,
  } = useHorarioContext();

  const generarHorarios = async (materiasIds, cantidad) => {
    fetchHorarioRequest();
    try {
      const data = await generarHorarioCompatibleService({
        materiasIds,
        cantidadHorarios: cantidad,
      });
      fetchHorarioSuccess(data);
      console.log("DATA DESDE EL BACKEND:", data);
      toast.success("Horarios generados");
    } catch (err) {
      fetchHorarioFailure(err);
      toast.error("Error al generar horarios");
    }
  };

  return { generarHorarios, cargando, resultados };
}

export function useFormHorario() {
  const { formHorario, updateFormHorario, clearFormHorario } = useHorarioContext();
  return { formHorario, updateFormHorario, clearFormHorario };
}