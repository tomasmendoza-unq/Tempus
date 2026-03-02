import { Link } from "react-router-dom"
import { useAuth } from "../../../hooks/useAuth"

export default function NavBar() {
  const { isAuthenticated, usuario, logout } = useAuth()

  return (
    <header className="flex items-center justify-between px-8 py-4 shadow bg-red-950 text-white">
      {/* Lado Izquierdo: Logo - Ancho fijo para equilibrar */}
      <div className="w-48">
        <h1 className="text-2xl font-bold">Tempus</h1>
      </div>

      {/* Centro: Navegación Principal */}
      <nav className="flex items-center gap-8 text-lg font-medium">
        <Link className="hover:text-red-400 transition-colors" to="/">
          Inicio
        </Link>
        
        {isAuthenticated && (
          <>
            <Link className="hover:text-red-400 transition-colors text-center leading-tight" to="/materias">
              Gestion de <br className="md:hidden" /> Materias
            </Link>
            <Link className="hover:text-red-400 transition-colors" to="/correlativas">
              Correlativas
            </Link>
            <Link className="hover:text-red-400 transition-colors" to="/horario">
              Horarios
            </Link>
          </>
        )}
      </nav>

      {/* Lado Derecho: Acciones - Ancho fijo igual al logo */}
      <div className="w-48 flex justify-end items-center gap-4">
        {!isAuthenticated ? (
          <Link className="hover:text-red-400 font-bold" to="/login">
            Login
          </Link>
        ) : (
          <div className="flex flex-col items-end">
            <span className="text-xs text-red-200 opacity-80 uppercase tracking-tighter">
              {usuario?.nombre}
            </span>
            <button 
              onClick={logout}
              className="text-sm font-bold hover:text-red-400 transition-colors bg-red-900/50 px-2 py-1 rounded"
            >
              Logout
            </button>
          </div>
        )}
      </div>
    </header>
  )
}