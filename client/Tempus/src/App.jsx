import { Link, Route, Routes } from "react-router-dom"
import "./App.css"
import Correlativas from "./pages/Correlativas"
import Home from "./pages/Home"
import Materias from "./pages/Materias"

function App() {
  return (
    <div className="min-h-screen bg-slate-100 text-slate-900">
      <header className="flex items-center justify-between px-6 py-4 shadow bg-white">
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

      <main className="px-6 py-8">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/materias" element={<Materias />} />
          <Route path="/correlativas" element={<Correlativas />} />
          <Route
            path="*"
            element={
              <p className="text-sm text-red-800">
                No se encontró la página que buscás.
              </p>
            }
          />
        </Routes>
      </main>
    </div>
  )
}

export default App
