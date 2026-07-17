import { useEffect } from "react"
import { Link } from "react-router-dom"
import { useAuth } from "../../../hooks/useAuth"
import { useUser } from "../../../hooks/useUser"
import HamburgerMenu from "./HamburgerMenu"
import CarreraSelector from "../../Carrera/CarreraSelector"

export default function NavBar() {
  const { isAuthenticated, usuario, logout } = useAuth()
  const { cargarDatosBasicos } = useUser()

  useEffect(() => {
    if (isAuthenticated) cargarDatosBasicos()
  }, [isAuthenticated])

  return (
    <header className="flex items-center justify-between px-8 py-4 shadow bg-red-950 text-white">
      <div className="flex items-center gap-8">
        <Link to="/" className="...">
          <h1 className="text-2xl font-bold">Tempus</h1>
        </Link>
        {isAuthenticated && <CarreraSelector />}
      </div>
      <HamburgerMenu
        isAuthenticated={isAuthenticated}
        usuario={usuario}
        logout={logout}
      />
    </header>
  )
}