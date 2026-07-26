import { usePostAction } from "../../../shared/hooks/use-post-action"
import { suscribirseCarrera as suscribirseService } from "../service/post-suscribirse-carrera.service"

export const UsePostSuscribirseCarrera = () => {
	const { ejecutar, loading, error } = usePostAction(suscribirseService)
	return { suscribirseCarrera: ejecutar, loading, error }
}
