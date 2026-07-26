import { usePostAction } from "../../../shared/hooks/use-post-action"
import { aprobarComisionService } from "../service/post-aprobar-comision.service"

export const UsePostAprobarCursada = () => {
	const { ejecutar, loading, error } = usePostAction(aprobarComisionService)
	return { aprobarCursada: ejecutar, loading, error }
}
