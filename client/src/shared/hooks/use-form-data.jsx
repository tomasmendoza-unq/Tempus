import { useState } from "react"

export const useFormData = (initialState) => {
	const [formData, setFormData] = useState(initialState)

	const handleChange = (e) => {
		const { name, value } = e.target
		setFormData((prev) => ({ ...prev, [name]: value }))
	}

	const setField = (name, value) => {
		setFormData((prev) => ({ ...prev, [name]: value }))
	}

	const reset = () => setFormData(initialState)

	return { formData, setFormData, handleChange, setField, reset }
}
