import { HorarioGrid } from "./HorarioGrid"
import { usePostIncribirseComisiones } from "../../feature/alumno/hook/use-post-inscribirse-comisiones"
import { toast } from "react-toastify"

export function ResultadoList({ resultados, cargando }) {
	const { inscribirse, loading, error } = usePostIncribirseComisiones()

	if (!cargando && (!resultados || resultados.length === 0)) {
		return (
			<div className="col-span-full text-center py-10 text-gray-400 italic">
				No hay combinaciones para mostrar. Seleccioná materias y hacé clic en
				Generar.
			</div>
		)
	}

	const onInscribirse = async (horario) => {
		const comisionesIds = horario.comisiones.map((c) => c.comisionId)
		inscribirse(comisionesIds)
		if (error) toast.error(error)
		else toast.success("Inscripción realizada con éxito")
	}

	return (
		<div className="grid grid-cols-1 md:grid-cols-2 gap-8">
			{resultados.map((horario, index) => (
				<div
					key={index}
					className="space-y-4 animate-fadeIn bg-white p-5 rounded-xl border border-gray-100 shadow-sm hover:shadow-md transition-all"
				>
					<div className="flex items-center justify-between">
						<div className="flex items-center gap-2">
							<span className="flex items-center justify-center bg-red-950 text-white w-8 h-8 rounded-full text-sm font-bold">
								{index + 1}
							</span>
							<h4 className="font-semibold text-gray-700 uppercase text-xs tracking-wider">
								Opción de Horario
							</h4>
						</div>

						<button
							onClick={() => onInscribirse(horario)}
							disabled={loading}
							className="px-4 py-2 bg-red-950 text-white text-xs font-bold rounded-lg hover:bg-red-900 disabled:bg-gray-400 transition-colors shadow-sm active:scale-95"
						>
							{loading ? "Inscribiendo..." : "Elegir este horario"}
						</button>
					</div>

					<HorarioGrid horario={horario} />
				</div>
			))}
		</div>
	)
}
