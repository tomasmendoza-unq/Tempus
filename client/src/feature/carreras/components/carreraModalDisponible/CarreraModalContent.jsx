import { BookOpen } from "feather-icons-react"
import { CarreraItem } from "../carreraItem/CarreraItem"
import { ModalHeader } from "../../../../shared/components/modal/ModalHeader"

export function CarreraModalContent({ carrerasDisponibles, onSeleccionar }) {
	return (
		<div className="w-full mt-4">
			<ModalHeader
				title="Oferta Académica"
				subtitle="Tempus - Inscripción Ciclo 2026"
			/>

			<div className="grid grid-cols-2 gap-3 max-h-[400px] overflow-y-auto pr-2 custom-scrollbar">
				{carrerasDisponibles?.length > 0 ? (
					carrerasDisponibles.map((carrera) => (
						<CarreraItem carrera={carrera}>
							<button
								onClick={() => onSeleccionar(carrera.idCarrera)}
								className="bg-red-950 text-white px-5 py-2 rounded-lg text-xs font-black uppercase tracking-tight hover:bg-red-800 transition-colors shadow-sm active:scale-95"
							>
								Inscribirme
							</button>
						</CarreraItem>
					))
				) : (
					<div className="py-12 text-center col-span-2">
						<p className="text-gray-400 italic text-sm">
							No hay más carreras disponibles para tu legajo.
						</p>
					</div>
				)}
			</div>
		</div>
	)
}
