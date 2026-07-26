import { useEffect } from "react"
import { InfoPersonal } from "../../../components/Perfil/InfoPersonal"
import { useGetAlumnoDetails } from "../hook/use-get-alumno-details"
import { SuscripcionCarreras } from "../components/suscribirseCarrera/SuscripcionCarreras"
import { Spinner } from "../../../shared/components/spinner/Spinner"
import { ComisionesAnotadas } from "../components/comisionesAnotadas/ComisionesAnotadas"
import { MateriasAprobadas } from "../components/materiasAprobadas/MateriasAprobadas"
import { useSuscripcionCarreras } from "../hook/use-suscripcion-carreras"
import { useCursadas } from "../hook/use-cursadas"

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
	const { handleAprobar, handleDesaprobar } = useCursadas(setAlumnoDetails)

	useEffect(() => {
		fetchAlumnoDetails()
	}, [])

	if (loading && !alumnoDetails) {
		return <Spinner />
	}

	return (
		<main className="min-h-screen p-6 flex flex-col items-center">
			<article className="bg-white p-8 rounded-xl shadow-lg w-full max-w-4xl border border-gray-100 space-y-8">
				{error && (
					<div className="bg-red-100 text-red-700 p-4 rounded-md">
						<p>{error.message}</p>
					</div>
				)}
				<InfoPersonal
					nombre={alumnoDetails.nombre}
					apellido={alumnoDetails.apellido}
					email={alumnoDetails.email}
				/>

				<SuscripcionCarreras
					carrerasUsuario={alumnoDetails.carreras}
					onSuscribir={handleSuscribir}
					onDesuscribir={handleDesuscribir}
				/>

				<section className="grid grid-cols-1 md:grid-cols-2 gap-8">
					<ComisionesAnotadas
						comisiones={alumnoDetails.comisiones}
						onAprobar={handleAprobar}
					/>

					<MateriasAprobadas
						materias={alumnoDetails.cursadas}
						onDesaprobar={handleDesaprobar}
					/>
				</section>
			</article>
		</main>
	)
}
