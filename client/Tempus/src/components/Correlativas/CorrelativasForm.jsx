import { useState } from "react"
import CorrelativasSelector from "./CorrelativasSelector"
import { useAsociarMateria } from "../../hooks/useMateria"
import Titulo from "../Ui/Titulo/Titulo"

export default function CorrelativasForm() {
  const [materiaOrigen, setMateriaOrigen] = useState("")
  const [materiaDestino, setMateriaDestino] = useState("")
  const { asociarMateria } = useAsociarMateria()

  return (
    <div className="flex flex-col">
      <Titulo texto="Asociar correlativas" />
      <form
        onSubmit={(e) => {
          e.preventDefault()
          asociarMateria(materiaOrigen.materiaId, materiaDestino.materiaId)
        }}
        className="flex flex-col justify-center gap-6 bg-white p-6 rounded-2xl shadow-md border border-gray-100 w-fit"
      >
        <div className="flex flex-row gap-6">
          <CorrelativasSelector
            setMateria={setMateriaOrigen}
            materia={materiaOrigen}
            titulo="Materia Origen"
            excludedId={materiaDestino?.materiaId}
          />
          <CorrelativasSelector
            setMateria={setMateriaDestino}
            materia={materiaDestino}
            titulo="Materia Destino"
            excludedId={materiaOrigen?.materiaId}
          />
        </div>
        <div className="flex flex-col items-center justify-center gap-3">
          {materiaDestino?.materiaId && materiaOrigen?.materiaId && (
            <p>
              Se va a asociar {materiaOrigen?.materiaNombre} con{" "}
              {materiaDestino?.materiaNombre}
            </p>
          )}
          <button
            type="submit"
            className={`flex bg-red-950 text-white px-4 py-2 rounded-lg hover:bg-red-900 transition disabled:opacity-50 disabled:cursor-not-allowed`}
            disabled={!materiaOrigen || !materiaDestino}
          >
            Asociar
          </button>
        </div>
      </form>
    </div>
  )
}
