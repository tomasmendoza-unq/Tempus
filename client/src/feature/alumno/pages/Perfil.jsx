import { useEffect } from "react"
import { InfoPersonal } from "../../../components/Perfil/InfoPersonal"
import { useGetAlumnoDetails } from "../hook/use-get-alumno-details"
import { UsePostSuscribirseCarrera } from "../hook/use-post-suscribirse-carrera"
import { SuscripcionCarreras } from "../components/suscribirseCarrera/SuscripcionCarreras"
import { UsePostDesuscribirseCarrera } from "../hook/use-post-desuscribirse-carrera"
import { Spinner } from "../../../shared/components/spinner/Spinner"
import { ComisionesAnotadas } from "../components/comisionesAnotadas/ComisionesAnotadas"
import { MateriasAprobadas } from "../components/materiasAprobadas/MateriasAprobadas"
import { useSuscripcionCarreras } from "../hook/use-suscripcion-carreras"

export const Perfil = () => {
	const {
		alumnoDetails,
		loading,
		error,
		fetchAlumnoDetails,
		setAlumnoDetails,
	} = useGetAlumnoDetails()

	const { handleSuscribir, handleDesuscribir } =
		useSuscripcionCarreras(setAlumnoDetails)

	useEffect(() => {
		fetchAlumnoDetails()
	}, [])

	if (loading && !alumnoDetails) {
		return <Spinner />
	}

	if (error) <p>{error.message}</p>

	return (
		<div className="min-h-screen p-6 flex flex-col items-center">
			<div className="bg-white p-8 rounded-xl shadow-lg w-full max-w-4xl border border-gray-100 space-y-8">
				<InfoPersonal
					nombre={alumnoDetails.nombre}
					apellido={alumnoDetails.apellido}
					email={alumnoDetails.email}
				/>

				<SuscripcionCarreras
					carrerasUsuario={alumnoDetails.carreras}
					onSuscribir={(idCarrera) => {
						handleSuscribir(idCarrera)
					}}
					onDesuscribir={(idCarrera) => {
						handleDesuscribir(idCarrera)
					}}
				/>

				<div className="grid grid-cols-1 md:grid-cols-2 gap-8">
					<ComisionesAnotadas
						comisiones={alumnoDetails.comisiones}
						// onAprobar={aprobarCursada}
					/>

					<MateriasAprobadas
						materias={alumnoDetails.materiaDTOResponseSimples}
						// onDesaprobar={desaprobarMateria}
					/>
				</div>
			</div>
		</div>
	)
}
