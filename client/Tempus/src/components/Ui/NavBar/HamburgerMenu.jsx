import { useState } from "react"
import { Link } from "react-router-dom"
import { Menu, X, User } from "feather-icons-react"

export default function HamburgerMenu({ isAuthenticated, usuario, logout }) {
  const [isOpen, setIsOpen] = useState(false)

  const closeMenu = () => setIsOpen(false)

  return (
    <>
      <button
        onClick={() => setIsOpen(true)}
        className="text-white hover:text-red-400 transition-colors z-50"
        aria-label="Abrir menú"
      >
        <Menu size={28} />
      </button>

      <div
        className={`fixed inset-0 bg-black/60 z-40 transition-opacity duration-300 ${
          isOpen ? "opacity-100 visible" : "opacity-0 invisible"
        }`}
        onClick={closeMenu}
      />

      <div
        className={`fixed top-0 right-0 h-full w-72 bg-red-950 z-50 shadow-2xl transform transition-transform duration-300 ease-in-out ${
          isOpen ? "translate-x-0" : "translate-x-full"
        }`}
      >
        <div className="flex items-center justify-between px-6 py-5 border-b border-red-900/50">
          <span className="text-white text-lg font-bold">Menú</span>
          <button
            onClick={closeMenu}
            className="text-red-300 hover:text-white transition-colors"
            aria-label="Cerrar menú"
          >
            <X size={24} />
          </button>
        </div>

        <nav className="flex flex-col px-4 py-4 gap-1">
          <NavLink to="/" onClick={closeMenu}>
            Inicio
          </NavLink>

          {isAuthenticated && (
            <>
              <hr className="my-3 border-red-900/50" />
              <div className="mb-1 px-4 text-[10px] uppercase font-black text-red-400/60 tracking-widest">
                Mi Espacio
              </div>
              <NavLink to="/perfil" onClick={closeMenu}>
                <div className="flex items-center gap-2">
                  <User size={18} /> Mi Perfil
                </div>
              </NavLink>
              <NavLink to="/horario" onClick={closeMenu}>
                Horarios
              </NavLink>

              <hr className="my-3 border-red-900/50" />
              <div className="mb-1 px-4 text-[10px] uppercase font-black text-red-400/60 tracking-widest">
                Planificación
              </div>
              <NavLink to="/materias" onClick={closeMenu}>
                Gestión de Materias
              </NavLink>
              <NavLink to="/correlativas" onClick={closeMenu}>
                Correlativas
              </NavLink>
              <NavLink to="/carreras" onClick={closeMenu}>
                Carreras
              </NavLink>
              <NavLink to="/comisiones" onClick={closeMenu}>
                Comisiones
              </NavLink>
            </>
          )}
        </nav>

        <div className="absolute bottom-0 left-0 right-0 px-6 py-5 border-t border-red-900/50">
          {!isAuthenticated ? (
            <Link
              to="/login"
              onClick={closeMenu}
              className="block w-full text-center bg-white text-red-950 font-bold py-2.5 rounded-lg hover:bg-red-100 transition-colors"
            >
              Iniciar Sesión
            </Link>
          ) : (
            <div className="flex flex-col gap-3">
              <span className="text-red-200 text-sm opacity-80 uppercase tracking-wide">
                {usuario?.nombre}
              </span>
              <button
                onClick={() => {
                  logout()
                  closeMenu()
                }}
                className="w-full bg-red-900/60 text-white font-bold py-2.5 rounded-lg hover:bg-red-800 transition-colors"
              >
                Cerrar Sesión
              </button>
            </div>
          )}
        </div>
      </div>
    </>
  )
}

function NavLink({ to, onClick, children }) {
  return (
    <Link
      to={to}
      onClick={onClick}
      className="text-red-100 hover:bg-red-900/60 hover:text-white px-4 py-3 rounded-lg transition-colors font-medium"
    >
      {children}
    </Link>
  )
}
