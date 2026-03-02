import { StrictMode } from "react"
import { createRoot } from "react-dom/client"
import { BrowserRouter } from "react-router-dom"
import "./index.css"
import App from "./App.jsx"
import { MateriaProvider } from "./contexts/MateriaContext"
import { HorarioProvider } from "./contexts/HorarioContext";
import { ToastContainer } from "react-toastify"

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <MateriaProvider>
      <HorarioProvider>
        <BrowserRouter>
          <App />
        </BrowserRouter>
      </HorarioProvider>
    </MateriaProvider>
    <ToastContainer position="bottom-right" autoClose={3000} />
  </StrictMode>
)
