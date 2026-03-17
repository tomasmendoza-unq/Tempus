import ComisionSelectorMateria from "../../components/Comision/ComisionSelectorMateria"
import ComisionMateriaSeleccionada from "../../components/Comision/ComisionMateriaSeleccionada"
import ComisionStepper from "../../components/Comision/ComisionStepper"
import ComisionHorarioForm from "../../components/Comision/ComisionHorarioForm"
import ComisionReview from "../../components/Comision/ComisionReview"
import { useComision, useFormComision } from "../../hooks/useComision"
import { useState } from "react"
import { ArrowLeft, ArrowRight } from "feather-icons-react"

export default function ComisionAgregar() {
  const { comision, setComision, clearFormComision } = useFormComision()

  const { crearComision } = useComision()

  const { step } = comision

  const canNext =
    (step === 0 && comision.materiaSeleccionada !== null) ||
    (step === 1 && comision.comision.horarios.length > 0)

  const siguiente = () => {
    if (canNext) {
      setComision((prev) => ({ ...prev, step: prev.step + 1 }))
    }
  }

  const anterior = () => {
    setComision((prev) => ({ ...prev, step: Math.max(prev.step - 1, 0) }))
  }

  const [enviando, setEnviando] = useState(false)

  const confirmarComision = async () => {
    setEnviando(true)

    try {
      const payload = {
        materiaId: comision.comision.materiaId,
        claseHorario: comision.comision.horarios.map((h) => ({
          dia: h.dia,
          inicio: h.horaInicio,
          fin: h.horaFin,
        })),
      }

      await crearComision(payload)
      clearFormComision()
    } finally {
      setEnviando(false)
    }
  }

  return (
    <div className="max-w-5xl mx-auto px-6 py-10">
      <h1 className="text-2xl font-bold mb-2 text-white text-center">
        Gestión de Comisiones
      </h1>

      <ComisionStepper currentStep={step} />

      <div className="mt-6">
        {step === 0 && (
          <div className="grid grid-cols-1 lg:grid-cols-2 gap-10">
            <ComisionSelectorMateria
              setMateriaSeleccionada={setComision}
              selectedId={comision.materiaSeleccionada?.materiaId}
            />
            {comision.materiaSeleccionada ? (
              <ComisionMateriaSeleccionada
                materiaSeleccionada={comision.materiaSeleccionada}
                setMateriaSeleccionada={setComision}
              />
            ) : (
              <p className="text-gray-500 self-center">
                Seleccioná una materia para continuar.
              </p>
            )}
          </div>
        )}

        {step === 1 && (
          <ComisionHorarioForm comision={comision} setComision={setComision} />
        )}

        {step === 2 && <ComisionReview comision={comision} />}
      </div>

      <div className="flex justify-between mt-8">
        {step > 0 ? (
          <button
            onClick={anterior}
            className="flex items-center gap-2 text-gray-300 hover:text-white border border-gray-600 hover:border-gray-400 px-5 py-2.5 rounded-lg transition-colors"
          >
            <ArrowLeft size={16} />
            Anterior
          </button>
        ) : (
          <div />
        )}

        {step < 2 ? (
          <button
            onClick={siguiente}
            disabled={!canNext}
            className={`flex items-center gap-2 px-5 py-2.5 rounded-lg font-semibold transition-colors ${
              canNext
                ? "bg-red-600 hover:bg-red-700 text-white"
                : "bg-gray-700 text-gray-500 cursor-not-allowed"
            }`}
          >
            Siguiente
            <ArrowRight size={16} />
          </button>
        ) : (
          <button
            onClick={confirmarComision}
            disabled={enviando}
            className={`flex items-center gap-2 font-semibold px-5 py-2.5 rounded-lg transition-colors ${
              enviando
                ? "bg-gray-600 text-gray-400 cursor-not-allowed"
                : "bg-green-600 hover:bg-green-700 text-white"
            }`}
          >
            {enviando ? "Enviando..." : "Confirmar Comisión"}
          </button>
        )}
      </div>
    </div>
  )
}
