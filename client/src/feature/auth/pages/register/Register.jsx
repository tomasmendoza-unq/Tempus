import { useEffect } from "react"
import { UseGetCarreras } from "../../../../feature/carreras/hook/use-get-carreras"
import SelectField from "../../../../components/Ui/Select/SelectField"
import AuthButton from "../../components/button/AuthButton"
import { inputs } from "./inputs"
import { useAuth } from "../../hook/use-auth"
import { SelectCarrera } from "../../../carreras/components/SelectCarrera"
import { useFormData } from "../../../../shared/hooks/use-form-data"
import AuthForm from "../../components/form/AuthForm"
import AuthLayout from "../../components/layout/AuthLayout"
import { LinkCustom } from "../../components/link/LinkCustom"
import { FormError } from "../../components/formError/FormError"

export default function Register() {
	const { register, loading, error } = useAuth()

	const { fetchCarreras, carreras } = UseGetCarreras()

	const { formData, handleChange } = useFormData({
		email: "",
		password: "",
		nombre: "",
		apellido: "",
		carreraId: "",
	})

	useEffect(() => {
		fetchCarreras()
	}, [])

	const handleSubmit = async (e) => {
		e.preventDefault()
		await register(formData)
	}

	return (
		<AuthLayout
			title="Registrate"
			subtitle="Crea tu cuenta"
			footer={
				<LinkCustom
					to="/login"
					text="¿Ya tenés cuenta?"
					textLink="Inicia sesión"
				/>
			}
		>
			<form onSubmit={handleSubmit} method="POST" className="space-y-4">
				<AuthForm
					inputs={inputs}
					formData={formData}
					handleChange={handleChange}
					extraProps={{
						carreraId: {
							options: carreras.map((c) => ({
								value: c.idCarrera,
								label: c.nombreCarrera,
							})),
							label: "Seleccionar Carrera",
						},
					}}
				/>
				{error && <FormError error={error} />}

				<AuthButton
					loading={loading}
					text="Registrarse"
					loadingText="Registrando..."
				/>
			</form>
		</AuthLayout>
	)
}
