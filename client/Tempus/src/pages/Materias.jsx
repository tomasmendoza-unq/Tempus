import { useState } from "react"
import FormAgregarMateria from "../components/Admin/FormAgregarMateria"
import MostrarMateria from "../components/Admin/MostrarMateria"
import { useFormMateria } from "../hooks/useMateria"
import CorrelativasForm from "../components/Correlativas/CorrelativasForm"
import { useMateriaContext } from "../hooks/ContextHooks/useMateriaContext"
import Tabs from "../components/Ui/Tabs/Tabs"

export default function Materias() {
  const { clearFormMateria } = useFormMateria()
  const { limpiarMaterias } = useMateriaContext()

  const [currentTab, setCurrentTab] = useState(0)

  const tabs = [
    { label: "Agregar materia", id: "agregar" },
    { label: "Buscar materia", id: "buscar" },
    { label: "Correlativas", id: "correlativas" },
  ]

  const handleTabChange = (index) => {
    setCurrentTab(index)
    clearFormMateria()
    limpiarMaterias()
  }

  return (
    <div className="flex flex-col p-6 gap-6 max-w-2xl mx-auto w-full">
      <Tabs tabs={tabs} currentTab={currentTab} onTabChange={handleTabChange} />
      {currentTab === 0 && <FormAgregarMateria />}
      {currentTab === 1 && <MostrarMateria />}
      {currentTab === 2 && <CorrelativasForm />}
    </div>
  )
}
