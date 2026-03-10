import { Link } from "react-router-dom"
import { useAuth } from "../../../hooks/useAuth"
import HamburgerMenu from "./HamburgerMenu"
import CarreraSelector from "../../Carrera/CarreraSelector"

export default function NavBar() {
  const { isAuthenticated, usuario, logout } = useAuth()

  return (
    <header className="flex items-center justify-between px-8 py-4 shadow bg-red-950 text-white">
      
      <div className="flex items-center gap-8">

        <Link
          to="/"
          className="
            relative inline-block
            after:absolute after:left-0 after:bottom-[-2px]
            after:h-[2px] after:w-full
            after:bg-red-400
            after:origin-left after:scale-x-0
            after:transition-transform after:duration-300
            hover:after:scale-x-100
          "
        >
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