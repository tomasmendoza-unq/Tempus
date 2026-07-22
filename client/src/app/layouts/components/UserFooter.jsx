import { useAuth } from "../../../feature/auth/hook/use-auth"
import "./UserFooter.css"

export const UserFooter = () => {
	const { logout } = useAuth()

	return (
		<div className="flex flex-col gap-3">
			<button
				className="user-button"
				onClick={() => {
					logout()
				}}
			>
				Cerrar Sesión
			</button>
		</div>
	)
}
