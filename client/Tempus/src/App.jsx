import { Route, Routes } from "react-router-dom"
import "./App.css"
import Correlativas from "./pages/Correlativas"
import Home from "./pages/Home"
import Materias from "./pages/Materias"
import NavBar from "./components/Ui/NavBar/NavBar"

function App() {
  return (
    <div className="min-h-screen text-slate-900">
      <NavBar />
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
