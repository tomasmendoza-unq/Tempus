import { Route, Routes, Navigate } from "react-router-dom"
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
import { getJwtPayload } from "./helpers/jwt"
import Carreras from "./pages/Carreras"
import PerfilPage from "./pages/PerfilPage"
import ComisionAgregar from "./pages/Comision/ComisionAgregar"
import ComisionMostrar from "./pages/Comision/ComisionMostrar"
import ComisionEditar from "./pages/Comision/ComisionEditar"
import ImportPage from "./pages/ImportPage"

function App() {
  const isAuthenticated = !!getJwtPayload()
  return (
    <div className="min-h-screen text-slate-900">
      <NavBar />
      <main className="px-6 py-8">
        <Routes>
          <Route path={ROUTES.HOME} element={<Home />} />

          <Route
            path={ROUTES.REGISTER}
            element={
              isAuthenticated ? (
                <Navigate to={ROUTES.HOME} replace />
              ) : (
                <RegisterPage />
              )
            }
          />
          <Route
            path={ROUTES.LOGIN}
            element={
              isAuthenticated ? (
                <Navigate to={ROUTES.HOME} replace />
              ) : (
                <LoginPage />
              )
            }
          />

          <Route
            path={ROUTES.PERFIL}
            element={
              <ProtectedRoute allowedRoles={["ROLE_USER", "ROLE_ADMIN"]}>
                <PerfilPage />
              </ProtectedRoute>
            }
          />
          <Route
            path={ROUTES.MATERIAS}
            element={
              <ProtectedRoute allowedRoles={["ROLE_ADMIN"]}>
                <Materias />
              </ProtectedRoute>
            }
          />
          <Route
            path={ROUTES.CORRELATIVAS}
            element={
              <ProtectedRoute allowedRoles={["ROLE_ADMIN"]}>
                <Correlativas />
              </ProtectedRoute>
            }
          />
          <Route
            path={ROUTES.HORARIOS}
            element={
              <ProtectedRoute allowedRoles={["ROLE_ADMIN"]}>
                <GeneradorHorarios />
              </ProtectedRoute>
            }
          />
          <Route
            path={ROUTES.CARRERAS}
            element={
              <ProtectedRoute allowedRoles={["ROLE_ADMIN"]}>
                <Carreras />
              </ProtectedRoute>
            }
          />
          <Route
            path={ROUTES.COMISION_AGREGAR}
            element={
              <ProtectedRoute allowedRoles={["ROLE_ADMIN"]}>
                <ComisionAgregar />
              </ProtectedRoute>
            }
          />
          <Route
            path={ROUTES.COMISION_MOSTRAR}
            element={
              <ProtectedRoute allowedRoles={["ROLE_ADMIN"]}>
                <ComisionMostrar />
              </ProtectedRoute>
            }
          />
          <Route
            path={ROUTES.COMISION_EDITAR}
            element={
              <ProtectedRoute allowedRoles={["ROLE_ADMIN"]}>
                <ComisionEditar />
              </ProtectedRoute>
            }
          />

          <Route
            path={ROUTES.IMPORT}
            element={
              <ProtectedRoute allowedRoles={["ROLE_ADMIN"]}>
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
