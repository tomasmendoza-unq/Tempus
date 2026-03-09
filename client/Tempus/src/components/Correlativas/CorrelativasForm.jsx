import { useState } from "react"
import CorrelativasSelector from "./CorrelativasSelector"
import { useAsociarMaterias } from "../../hooks/useMateria"
import Titulo from "../Ui/Titulo/Titulo"

export default function CorrelativasForm() {
  const [materiaOrigen, setMateriaOrigen] = useState("")
  const [materiasDestino, setMateriasDestino] = useState([])
  const { asociarMaterias } = useAsociarMaterias()

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      const materias = {
        materiaOrigenId: materiaOrigen.materiaId,
        materiasDestinoIds: materiasDestino.map((m) => m.materiaId),
      }
      await asociarMaterias(materias)
    } catch (err) {
      console.error("Error al asociar materias:", err)
    }
  }

  return (
    <div className="flex flex-col">
      <Titulo texto="Asociar correlativas" />
      <form
        onSubmit={(e) => handleSubmit(e)}
        className="flex flex-col justify-center gap-6 bg-white p-6 rounded-2xl shadow-md border border-gray-100 w-fit"
      >
        <div className="flex flex-row gap-6">
          <CorrelativasSelector
            setMateria={setMateriaOrigen}
            materia={materiaOrigen}
            titulo="Materia Origen"
            excludedId={materiasDestino.map((m) => m.materiaId)}
          />
          <CorrelativasSelector
            setMateria={setMateriasDestino}
            materia={materiasDestino}
            titulo="Materias Destino"
            excludedId={materiaOrigen?.materiaId}
            multiple
          />
        </div>
        <div className="flex flex-col items-center justify-center gap-3">
          {materiasDestino.length > 0 && materiaOrigen?.materiaId && (
            <p>
              Se va a asociar {materiaOrigen?.materiaNombre} con{" "}
              {materiasDestino.map((m) => m.materiaNombre).join(", ")}
            </p>
          )}
          <button
            type="submit"
            className={`flex bg-red-950 text-white px-4 py-2 rounded-lg hover:bg-red-900 transition disabled:opacity-50 disabled:cursor-not-allowed`}
            disabled={!materiaOrigen || materiasDestino.length === 0}
          >
            Asociar
          </button>
        </div>
      </form>
    </div>
  )
}
