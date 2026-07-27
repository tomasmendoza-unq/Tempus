import { usePostAction } from "../../../shared/hooks/use-post-action"
import { inscribirseComisionesService } from "../service/post-inscribirse-comision.service"

export const usePostIncribirseComisiones = () => {
	const { ejecutar, loading, error } = usePostAction(
		inscribirseComisionesService
	)

	return { inscribirse: ejecutar, loading, error }
}
