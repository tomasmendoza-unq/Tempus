import { Link } from "react-router-dom"
import { useAuth } from "../../../hooks/useAuth"
import HamburgerMenu from "./HamburgerMenu"
import CarreraSelector from "../../Carrera/CarreraSelector"

export default function NavBar() {
  const { isAuthenticated, usuario, logout } = useAuth()

  return (
    <header className="flex items-center justify-between px-8 py-4 shadow bg-red-950 text-white">
      <div className="flex items-center gap-8">
        <h1 className="text-2xl font-bold">Tempus</h1>
        
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
