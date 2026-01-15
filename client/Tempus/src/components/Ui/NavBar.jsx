import { Link } from "react-router-dom"

export default function NavBar() {
  return (
    <header className="flex items-center justify-between px-6 py-4 shadow bg-red-950 text-white">
      <h1 className="text-xl font-semibold">Tempus</h1>
      <nav className="flex gap-4 text-sm font-medium">
        <Link className="hover:text-red-700" to="/">
          Inicio
        </Link>
        <Link className="hover:text-red-700" to="/materias">
          Materias
        </Link>
        <Link className="hover:text-red-700" to="/correlativas">
          Correlativas
        </Link>
      </nav>
    </header>
  )
}
