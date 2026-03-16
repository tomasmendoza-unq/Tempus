import { Route, Routes } from "react-router-dom"
import "./App.css"
import Correlativas from "./pages/Correlativas"
import Home from "./pages/Home"
import Materias from "./pages/Materias"
import NavBar from "./components/Ui/NavBar/NavBar"
import { ROUTES } from "../constants"
import GeneradorHorarios from "./pages/Horarios"
import RegisterPage from "./pages/RegisterPage"
import LoginPage from "./pages/LoginPage"
import { ProtectedRoute } from "./hooks/ProtectedRoute"
import Carreras from "./pages/Carreras"
import PerfilPage from "./pages/PerfilPage"
import Comision from "./pages/Comision"
import ImportPage from "./pages/ImportPage"

function App() {
  return (
    <div className="min-h-screen text-slate-900">
      <NavBar />
      <main className="px-6 py-8">
        <Routes>
          <Route path={ROUTES.HOME} element={<Home />} />
          <Route path={ROUTES.REGISTER} element={<RegisterPage />} />
          <Route path={ROUTES.LOGIN} element={<LoginPage />} />

          <Route
            path={ROUTES.PERFIL}
            element={
              <ProtectedRoute>
                <PerfilPage />
              </ProtectedRoute>
            }
          />
          <Route
            path={ROUTES.MATERIAS}
            element={
              <ProtectedRoute>
                <Materias />
              </ProtectedRoute>
            }
          />
          <Route
            path={ROUTES.CORRELATIVAS}
            element={
              <ProtectedRoute>
                <Correlativas />
              </ProtectedRoute>
            }
          />
          <Route
            path={ROUTES.HORARIOS}
            element={
              <ProtectedRoute>
                <GeneradorHorarios />
              </ProtectedRoute>
            }
          />
          <Route
            path={ROUTES.CARRERAS}
            element={
              <ProtectedRoute>
                <Carreras />
              </ProtectedRoute>
            }
          />
          <Route
            path={ROUTES.COMISIONES}
            element={
              <ProtectedRoute>
                <Comision />
              </ProtectedRoute>
            }
          />

          <Route
            path={ROUTES.IMPORT}
            element={
              <ProtectedRoute>
                <ImportPage />
              </ProtectedRoute>
            }
          />
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
