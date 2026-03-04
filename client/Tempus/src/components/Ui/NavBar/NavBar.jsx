import { Link } from "react-router-dom"
import { useAuth } from "../../../hooks/useAuth"
import HamburgerMenu from "./HamburgerMenu"

export default function NavBar() {
  const { isAuthenticated, usuario, logout } = useAuth()

  return (
    <header className="flex items-center justify-between px-8 py-4 shadow bg-red-950 text-white">
      <h1 className="text-2xl font-bold">Tempus</h1>

      <HamburgerMenu
        isAuthenticated={isAuthenticated}
        usuario={usuario}
        logout={logout}
      />
    </header>
  )
}
