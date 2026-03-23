import ComisionesSubmenu from "./ComisionesSubmenu"
import { User } from "feather-icons-react"
import { NavLink } from "./HamburgerMenu"

export default function MenuSection({
  perfilLabel = "Mi Perfil",
  closeMenu,
  children,
  showComisiones = false,
}) {
  return (
    <>
      <hr className="my-3 border-red-900/50" />
      <div className="mb-1 px-4 text-[10px] uppercase font-black text-red-400/60 tracking-widest">
        Mi Espacio
      </div>
      <NavLink to="/perfil" onClick={closeMenu}>
        <div className="flex items-center gap-2">
          <User size={18} /> {perfilLabel}
        </div>
      </NavLink>
      {children}
      {showComisiones && <ComisionesSubmenu closeMenu={closeMenu} />}
    </>
  )
}
