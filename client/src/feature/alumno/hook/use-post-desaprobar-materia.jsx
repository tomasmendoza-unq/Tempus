import { usePostAction } from "../../../shared/hooks/use-post-action"
import { desaprobarMateriaService } from "../service/post-desaprobar-materia.service"

export const UsePostDesaprobarMateria = () => {
	const { ejecutar, loading, error } = usePostAction(desaprobarMateriaService)

	return { desaprobarMateria: ejecutar, loading, error }
}
