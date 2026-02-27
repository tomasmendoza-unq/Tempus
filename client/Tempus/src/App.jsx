import { Route, Routes } from "react-router-dom"
import "./App.css"
import Correlativas from "./pages/Correlativas"
import Home from "./pages/Home"
import Materias from "./pages/Materias"
import NavBar from "./components/Ui/NavBar/NavBar"
import { ROUTES } from "../constants"

function App() {
  return (
    <div className="min-h-screen text-slate-900">
      <NavBar />
      <main className="px-6 py-8">
        <Routes>
          <Route path={ROUTES.HOME} element={<Home />} />
          <Route path={ROUTES.MATERIAS} element={<Materias />} />
          <Route path={ROUTES.CORRELATIVAS} element={<Correlativas />} />
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
