import { useMemo } from "react"

export const useComisionesPorMateria = (comisiones) => {
	return useMemo(() => {
		return comisiones.reduce((grupos, comision) => {
			const materiaNombre = comision.materia?.materiaNombre || "Comisiones"
			const materiaId = comision.materia?.materiaId || materiaNombre
			const grupoExistente = grupos.find((grupo) => grupo.id === materiaId)

			if (grupoExistente) {
				grupoExistente.comisiones.push(comision)
				return grupos
			}

			return [
				...grupos,
				{
					id: materiaId,
					nombre: materiaNombre,
					comisiones: [comision],
				},
			]
		}, [])
	}, [comisiones])
}
