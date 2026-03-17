import ComisionSelectorMateria from "../components/Comision/ComisionSelectorMateria"
import ComisionMateriaSeleccionada from "../components/Comision/ComisionMateriaSeleccionada"
import ComisionStepper from "../components/Comision/ComisionStepper"
import ComisionHorarioForm from "../components/Comision/ComisionHorarioForm"
import ComisionReview from "../components/Comision/ComisionReview"
import { crearComisionService } from "../services/comisionService"
import { useState } from "react"
import { ArrowLeft, ArrowRight } from "feather-icons-react"
import { toast } from "react-toastify"

export default function Comision() {
  const [comision, setComision] = useState({
    comision: {
      materiaId: null,
      horarios: [],
    },
    step: 0,
    materiaSeleccionada: null,
  })

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
      await crearComisionService(payload)
      toast.success("Comisión creada exitosamente.")
    } catch (error) {
      toast.error("Error al crear la comisión. Intentá de nuevo.")
    } finally {
      setEnviando(false)
      setComision({
        comision: {
          materiaId: null,
          horarios: [],
        },
        step: 0,
        materiaSeleccionada: null,
      })
    }
  }

  return (
    <div className="min-h-screen bg-[#121212] text-white p-4 md:p-8">
      <div className="max-w-[1600px] mx-auto w-full flex flex-col gap-8">
        
        <header className="text-center space-y-2">
          <h1 className="text-3xl font-bold">Gestión de Comisiones</h1>
          <div className="max-w-2xl mx-auto py-4">
            <ComisionStepper currentStep={step} />
          </div>
        </header>

        <main className="mt-4">
          {step === 0 && (
            /* Grid de 12 columnas: 
               - El selector ocupa 8 (66%) para que las cards sean legibles.
               - La materia seleccionada ocupa 4 (33%).
            */
            <div className="grid grid-cols-1 lg:grid-cols-12 gap-8 items-start">
              
              {/* PANEL IZQUIERDO: Buscador y Listado */}
              <div className="lg:col-span-8 bg-[#1e1e1e] rounded-2xl p-6 shadow-xl border border-white/5 overflow-hidden">
                <div className="max-h-[65vh] overflow-y-auto pr-2 custom-scrollbar relative">
                  <ComisionSelectorMateria
                    setMateriaSeleccionada={setComision}
                    selectedId={comision.materiaSeleccionada?.materiaId}
                    /* Forzamos que el buscador se mantenga arriba sin bugs visuales */
                    buscadorClassName="sticky top-0 z-30 bg-[#1e1e1e] pb-4"
                  />
                </div>
              </div>

              {/* PANEL DERECHO: Resumen Sticky */}
              <div className="lg:col-span-4 lg:sticky lg:top-8">
                {comision.materiaSeleccionada ? (
                  <div className="bg-[#1e1e1e] rounded-2xl p-1 border border-white/5 shadow-lg">
                    <ComisionMateriaSeleccionada
                      materiaSeleccionada={comision.materiaSeleccionada}
                      setMateriaSeleccionada={setComision}
                    />
                  </div>
                ) : (
                  <div className="bg-[#1e1e1e] border-2 border-dashed border-gray-700 rounded-2xl p-12 text-center">
                    <p className="text-gray-500 font-medium">
                      Seleccioná una materia para comenzar con la comisión.
                    </p>
                  </div>
                )}
              </div>
            </div>
          )}

          {step === 1 && (
            <div className="max-w-4xl mx-auto bg-[#1e1e1e] p-8 rounded-2xl border border-white/5">
              <ComisionHorarioForm comision={comision} setComision={setComision} />
            </div>
          )}

          {step === 2 && (
            <div className="max-w-4xl mx-auto bg-[#1e1e1e] p-8 rounded-2xl border border-white/5">
              <ComisionReview comision={comision} />
            </div>
          )}
        </main>

        {/* NAVEGACIÓN INFERIOR */}
        <footer className="flex justify-between items-center mt-4 pb-10">
          {step > 0 ? (
            <button
              onClick={anterior}
              className="flex items-center gap-2 text-gray-300 hover:text-white border border-gray-600 px-6 py-3 rounded-xl transition-all"
            >
              <ArrowLeft size={18} />
              Anterior
            </button>
          ) : (
            <div />
          )}

          {step < 2 ? (
            <button
              onClick={siguiente}
              disabled={!canNext}
              className={`flex items-center gap-2 px-8 py-3 rounded-xl font-bold transition-all ${
                canNext
                  ? "bg-red-700 hover:bg-red-600 text-white shadow-lg shadow-red-900/20"
                  : "bg-gray-800 text-gray-500 cursor-not-allowed"
              }`}
            >
              Siguiente
              <ArrowRight size={18} />
            </button>
          ) : (
            <button
              onClick={confirmarComision}
              disabled={enviando}
              className={`flex items-center gap-2 font-bold px-8 py-3 rounded-xl transition-all ${
                enviando
                  ? "bg-gray-700 text-gray-400 cursor-not-allowed"
                  : "bg-green-600 hover:bg-green-500 text-white"
              }`}
            >
              {enviando ? "Enviando..." : "Confirmar Comisión"}
            </button>
          )}
        </footer>
      </div>
    </div>
  )
}