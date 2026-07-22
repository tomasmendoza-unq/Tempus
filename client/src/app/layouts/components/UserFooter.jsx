import { useAuth } from "../../../feature/auth/hook/use-auth"

export const UserFooter = () => {
	const { usuario, logout } = useAuth()

	return (
		<div className="flex flex-col gap-3">
			<span>{usuario?.nombre}</span>
			<button
				onClick={() => {
					logout()
				}}
			>
				Cerrar Sesión
			</button>
		</div>
	)
}
