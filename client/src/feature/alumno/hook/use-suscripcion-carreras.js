import { UsePostSuscribirseCarrera } from "./use-post-suscribirse-carrera"
import { UsePostDesuscribirseCarrera } from "./use-post-desuscribirse-carrera"

export const useSuscripcionCarreras = (setAlumnoDetails) => {
	const { suscribirseCarrera } = UsePostSuscribirseCarrera()
	const { desuscribirseCarrera } = UsePostDesuscribirseCarrera()

	const handleSuscribir = async (idCarrera) => {
		const carreraAgregada = await suscribirseCarrera(idCarrera)
		if (!carreraAgregada) return

		setAlumnoDetails((prev) => ({
			...prev,
			carreras: [...prev.carreras, carreraAgregada],
		}))
	}

	const handleDesuscribir = async (idCarrera) => {
		const carreraEliminada = await desuscribirseCarrera(idCarrera)
		if (!carreraEliminada) return

		setAlumnoDetails((prev) => ({
			...prev,
			carreras: prev.carreras.filter(
				(c) => c.idCarrera !== carreraEliminada.idCarrera
			),
		}))
	}

	return { handleSuscribir, handleDesuscribir }
}
