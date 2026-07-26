import { UsePostAprobarCursada } from "./use-post-aprobar-cursada.jsx"
import { UsePostDesaprobarMateria } from "./use-post-desaprobar-materia.jsx"

export const useCursadas = (setAlumnoDetails) => {
	const { aprobarCursada } = UsePostAprobarCursada()
	const { desaprobarMateria } = UsePostDesaprobarMateria()

	const handleAprobar = async (comisionId) => {
		const materiaAprobada = await aprobarCursada(comisionId)
		if (!materiaAprobada) return

		setAlumnoDetails((prev) => ({
			...prev,
			cursadas: [...prev.cursadas, materiaAprobada],
		}))
	}
	const handleDesaprobar = async (materiaId) => {
		const materiaDesaprobada = await desaprobarMateria(materiaId)

		if (!materiaDesaprobada) return

		setAlumnoDetails((prev) => ({
			...prev,
			cursadas: prev.cursadas.filter(
				(c) => c.idMateria !== materiaDesaprobada.idMateria
			),
		}))
	}

	return { handleAprobar, handleDesaprobar }
}
