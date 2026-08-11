import Button from "../../../../shared/components/button/Button"
import DynamicForm from "../../../../shared/components/form/DynamicForm"
import { inputs } from "./inputs"

export const FormHorario = ({ formData, handleChange, onSubmit, loading }) => (
	<form
		onSubmit={onSubmit}
		method="POST"
		className="bg-white p-4 rounded-lg shadow border border-gray-200 flex flex-wrap gap-4 items-end justify-center"
	>
		<DynamicForm
			inputs={inputs}
			formData={formData}
			handleChange={handleChange}
		/>
		<Button
			type="submit"
			disabled={loading}
			loadingText="Calculando..."
			text="Generar Combinaciones"
		/>
	</form>
)
