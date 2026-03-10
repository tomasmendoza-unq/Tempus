import { StrictMode } from "react"
import { createRoot } from "react-dom/client"
import { BrowserRouter } from "react-router-dom"
import "./index.css"
import App from "./App.jsx"
import { MateriaProvider } from "./contexts/MateriaContext"
import { HorarioProvider } from "./contexts/HorarioContext";
import { AuthProvider } from "./contexts/AuthContext.jsx";
import { ToastContainer } from "react-toastify"
import { UserProvider } from "./contexts/UserContext.jsx"

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <MateriaProvider>
      <HorarioProvider>      
        <AuthProvider>
          <UserProvider>
            <BrowserRouter>
              <App />
            </BrowserRouter>
          </UserProvider>
        </AuthProvider>
      </HorarioProvider>
    </MateriaProvider>
    <ToastContainer position="bottom-right" autoClose={3000} />
  </StrictMode>
)
