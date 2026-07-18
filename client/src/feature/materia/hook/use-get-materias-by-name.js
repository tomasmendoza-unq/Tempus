import { useState } from "react"
import { getMateriasByNameService } from "../services/get-materias-by-name.service"

export const useGetMateriaByName = () => {
	const [materia, setMaterias] = useState([])
	const [loading, setLoading] = useState(false)
	const [error, setError] = useState(null)

	const fetchMateria = async (name) => {
		try {
			setLoading(true)
			const data = await getMateriasByNameService(name)
			setMaterias(data)
		} catch (err) {
			setError(err.message)
		} finally {
			setLoading(false)
		}
	}

	return { materia, loading, error, fetchMateria }
}
