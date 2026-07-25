import { useEffect } from "react"
import { InfoPersonal } from "../../../components/Perfil/InfoPersonal"
import { ListaCursadas } from "../../../components/Perfil/ListaCursadas"
import { ListaFinales } from "../../../components/Perfil/ListaFinales"
import { useGetAlumnoDetails } from "../hook/use-get-alumno-details"
import { UsePostSuscribirseCarrera } from "../hook/use-post-suscribirse-carrera"
import { SuscripcionCarreras } from "../components/suscribirseCarrera/SuscripcionCarreras"

export const Perfil = () => {
	const { alumnoDetails, loading, error, fetchAlumnoDetails } =
		useGetAlumnoDetails()

	const { suscribirseCarrera } = UsePostSuscribirseCarrera()
	useEffect(() => {
		fetchAlumnoDetails()
	}, [])

	if (loading && !alumnoDetails) {
		return (
			<div className="min-h-screen flex items-center justify-center">
				<div className="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-red-950"></div>
			</div>
		)
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
					onSuscribir={suscribirseCarrera}
					// onObtenerCarreras={obtenerCarrerasDisponibles}
				/>

				<div className="grid grid-cols-1 md:grid-cols-2 gap-8">
					<ListaCursadas
						comisiones={alumnoDetails.comisiones}
						// onAprobar={aprobarCursada}
					/>

					<ListaFinales
						materias={alumnoDetails.materiaDTOResponseSimples}
						// onDesaprobar={desaprobarMateria}
					/>
				</div>
			</div>
		</div>
	)
}
