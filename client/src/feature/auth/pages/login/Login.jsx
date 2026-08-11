import { useAuth } from "../../hook/use-auth"
import { Link } from "react-router-dom"
import AuthLayout from "../../components/layout/AuthLayout"
import { inputs } from "./inputs"
import { useFormData } from "../../../../shared/hooks/use-form-data"
import { LinkCustom } from "../../components/link/LinkCustom"
import { FormError } from "../../components/formError/FormError"
import DynamicForm from "../../../../shared/components/form/DynamicForm"
import Button from "../../../../shared/components/button/Button"

export default function Login() {
	const { login, loading, error } = useAuth()

	const { formData, handleChange } = useFormData({
		email: "",
		password: "",
	})

	const handleSubmit = async (e) => {
		e.preventDefault()
		await login(formData)
	}

	return (
		<AuthLayout
			title="Tempus"
			subtitle="Ingresá a tu panel de horarios"
			footer={
				<LinkCustom
					to="/register"
					text="¿No tenés cuenta?"
					textLink="Registrate"
				/>
			}
		>
			<form onSubmit={handleSubmit} className="space-y-4">
				<DynamicForm
					inputs={inputs}
					formData={formData}
					handleChange={handleChange}
				/>
				{error && <FormError error={error} />}

				<Button
					type="submit"
					loading={loading}
					text="Iniciar Sesión"
					loadingText="Validando..."
				/>
			</form>
		</AuthLayout>
	)
}
