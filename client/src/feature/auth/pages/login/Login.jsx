import { useAuth } from "../../hook/use-auth"
import { Link } from "react-router-dom"
import AuthLayout from "../../components/layout/AuthLayout"
import AuthInput from "../../components/input/AuthInput"
import AuthButton from "../../components/button/AuthButton"
import AuthForm from "../../components/form/AuthForm"
import { inputs } from "./inputs"
import { useFormData } from "../../../../shared/hooks/use-form-data"
import { LinkCustom } from "../../components/link/LinkCustom"
import { FormError } from "../../components/formError/FormError"

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

	if (error) <FormError message={error} />

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
				<AuthForm
					inputs={inputs}
					formData={formData}
					handleChange={handleChange}
				/>

				<AuthButton
					loading={loading}
					text="Iniciar Sesión"
					loadingText="Validando..."
				/>
			</form>
		</AuthLayout>
	)
}
