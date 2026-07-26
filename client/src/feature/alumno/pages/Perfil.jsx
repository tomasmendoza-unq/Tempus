import { useEffect } from "react"
import { InfoPersonal } from "../../../components/Perfil/InfoPersonal"
import { ListaCursadas } from "../../../components/Perfil/ListaCursadas"
import { ListaFinales } from "../../../components/Perfil/ListaFinales"
import { useGetAlumnoDetails } from "../hook/use-get-alumno-details"
import { UsePostSuscribirseCarrera } from "../hook/use-post-suscribirse-carrera"
import { SuscripcionCarreras } from "../components/suscribirseCarrera/SuscripcionCarreras"
import { UsePostDesuscribirseCarrera } from "../hook/use-post-desuscribirse-carrera"
import { Spinner } from "../../../shared/components/spinner/Spinner"
import { ListMaterias } from "../../materia/components/listMaterias/ListMaterias"
import { ListComisiones } from "../../comision/components/listComisiones/ListComisiones"

export const Perfil = () => {
	const {
		alumnoDetails,
		loading,
		error,
		fetchAlumnoDetails,
		setAlumnoDetails,
	} = useGetAlumnoDetails()

	const { suscribirseCarrera } = UsePostSuscribirseCarrera()

	const { desuscribirseCarrera } = UsePostDesuscribirseCarrera()

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

	const handleSuscribir = async (idCarrera) => {
		const carreraAgregada = await suscribirseCarrera(idCarrera)
		if (!carreraAgregada) return

		setAlumnoDetails((prev) => ({
			...prev,
			carreras: [...prev.carreras, carreraAgregada],
		}))
	}

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
					<ListComisiones
						comisiones={alumnoDetails.comisiones}
						// onAprobar={aprobarCursada}
					/>

					<ListMaterias
						materias={alumnoDetails.materiaDTOResponseSimples}
						// onDesaprobar={desaprobarMateria}
					/>
				</div>
			</div>
		</div>
	)
}
