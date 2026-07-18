import { StrictMode } from "react"
import { createRoot } from "react-dom/client"
import "./index.css"
import App from "./App.jsx"
import { MateriaProvider } from "./contexts/MateriaContext"
import { HorarioProvider } from "./contexts/HorarioContext"
import { ComisionProvider } from "./contexts/ComisionContext"
import { AuthProvider } from "./contexts/AuthContext.jsx"
import { ToastContainer } from "react-toastify"
import { UserProvider } from "./contexts/UserContext.jsx"
import { AuthProvider as AuthProviderRf } from "./feature/auth/context/authProvider.jsx"

createRoot(document.getElementById("root")).render(
	<StrictMode>
		<MateriaProvider>
			<HorarioProvider>
				<ComisionProvider>
					<AuthProviderRf>
						<AuthProvider>
							<UserProvider>
								<App />
							</UserProvider>
						</AuthProvider>
					</AuthProviderRf>
				</ComisionProvider>
			</HorarioProvider>
		</MateriaProvider>
		<ToastContainer position="bottom-right" autoClose={3000} />
	</StrictMode>
)
