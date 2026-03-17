import { useState } from "react"
import { Link } from "react-router-dom"
import { ChevronDown } from "feather-icons-react"

export default function ComisionesSubmenu({ closeMenu }) {
  const [isOpen, setIsOpen] = useState(false)

  return (
    <>
      <button
        onClick={() => setIsOpen(!isOpen)}
        className="flex items-center justify-between text-red-100 hover:bg-red-900/60 hover:text-white px-4 py-3 rounded-lg transition-colors font-medium w-full text-left"
      >
        Comisiones
        <ChevronDown
          size={18}
          className={`transition-transform duration-200 ${isOpen ? "rotate-180" : ""}`}
        />
      </button>
      {isOpen && (
        <div className="flex flex-col gap-1 pl-4">
          <Link
            to="/comisiones/mostrar"
            onClick={closeMenu}
            className="text-red-100 hover:bg-red-900/60 hover:text-white px-4 py-3 rounded-lg transition-colors font-medium"
          >
            Mostrar Comisiones
          </Link>
          <Link
            to="/comisiones/agregar"
            onClick={closeMenu}
            className="text-red-100 hover:bg-red-900/60 hover:text-white px-4 py-3 rounded-lg transition-colors font-medium"
          >
            Agregar Comisiones
          </Link>
        </div>
      )}
    </>
  )
}
