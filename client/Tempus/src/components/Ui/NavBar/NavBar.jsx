import { Link } from "react-router-dom"

export default function NavBar() {
  return (
    <header className="grid grid-cols-3 items-center px-6 py-4 shadow bg-red-950 text-white">
      <div className="justify-self-start">
        <h1 className="text-2xl font-bold">Tempus</h1>
      </div>

      <nav className="col-start-2 justify-self-center flex gap-10 text-xl font-medium">
        <Link className="hover:text-red-700" to="/">
          Inicio
        </Link>
        <Link className="hover:text-red-700" to="/materias">
          Gestion de Materias
        </Link>
        <Link className="hover:text-red-700" to="/correlativas">
          Correlativas
        </Link>
      </nav>
    </header>
  )
}
