import { useEffect } from "react"
import { Link } from "react-router-dom"
import useCarrera from "../../../../hooks/useCarrera"
import SelectField from "../../../../components/Ui/Select/SelectField"
import AuthInput from "../../components/AuthInput"
import AuthButton from "../../components/AuthButton"
import { inputs } from "./inputs"
import { useAuth } from "../../hook/use-auth"
import { SelectCarrera } from "../../../carreras/components/SelectCarrera"
import { useFormData } from "../../../../shared/hooks/use-form-data"
import AuthForm from "../../components/AuthForm"
import AuthLayout from "../../components/layout/AuthLayout"

export default function Register() {
	const { register, loading } = useAuth()

	const { recuperarCarreras, carreras } = useCarrera()

	const { formData, handleChange } = useFormData({
		email: "",
		password: "",
		nombre: "",
		apellido: "",
		telefono: "",
		carreraId: "",
	})

	useEffect(() => {
		recuperarCarreras()
	}, [])

	const handleSubmit = async (e) => {
		e.preventDefault()
		await register(formData)
	}

	return (
		<AuthLayout
			title="Registrate"
			subtitle="Crea tu cuenta"
			footer={<Link to="/login">¿Ya tenés cuenta? Inicia sesión</Link>}
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
				<AuthButton
					loading={loading}
					text="Registrarse"
					loadingText="Registrando..."
				/>
			</form>
		</AuthLayout>
	)
}
