import { useState } from "react"
import { useAuth } from "../../hook/use-auth"
import { Link } from "react-router-dom"

import AuthLayout from "../../components/layout/AuthLayout"
import AuthInput from "../../components/AuthInput"
import AuthButton from "../../components/AuthButton"

export default function Login() {
	const { login, loading } = useAuth()

	const [credentials, setCredentials] = useState({
		email: "",
		password: "",
	})

	const handleChange = (e) => {
		setCredentials({
			...credentials,
			[e.target.name]: e.target.value,
		})
	}

	const handleSubmit = async (e) => {
		e.preventDefault()
		await login(credentials)
	}

	return (
		<AuthLayout title="Tempus">
			<p className="text-center text-gray-500 text-sm -mt-2">
				Ingresá a tu panel de horarios
			</p>

			<form onSubmit={handleSubmit} className="space-y-4">
				<AuthInput
					name="email"
					type="email"
					placeholder="Email"
					onChange={handleChange}
				/>

				<AuthInput
					name="password"
					type="password"
					placeholder="Contraseña"
					onChange={handleChange}
				/>

				<AuthButton
					loading={loading}
					text="Iniciar Sesión"
					loadingText="Validando..."
				/>

				<p className="text-center text-sm text-gray-600 pt-2">
					¿No tenés cuenta?{" "}
					<Link
						to="/register"
						className="text-red-900 font-bold hover:underline"
					>
						Registrate
					</Link>
				</p>
			</form>
		</AuthLayout>
	)
}
