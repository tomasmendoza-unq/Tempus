import { usePostAction } from "../../../shared/hooks/use-post-action"
import { horarioCompatibleService } from "../service/post-horario-compatible.service"

export function usePostHorarioCompatible() {
	const { ejecutar, loading, error } = usePostAction(horarioCompatibleService)
	return { generarHorarios: ejecutar, loading, error }
}
