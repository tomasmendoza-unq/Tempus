import { Check } from "feather-icons-react"

const STEPS = [
  { label: "Materia", description: "Seleccionar materia" },
  { label: "Horarios", description: "Agregar días y horarios" },
  { label: "Revisión", description: "Confirmar comisión" },
]

export default function ComisionStepper({ currentStep }) {
  return (
    <div className="flex items-center justify-center w-full mb-10">
      {STEPS.map((step, index) => {
        const isCompleted = index < currentStep
        const isActive = index === currentStep

        return (
          <div key={step.label} className="flex items-center">
            <div className="flex flex-col items-center">
              <div
                className={`w-10 h-10 rounded-full flex items-center justify-center text-sm font-bold border-2 transition-all duration-300 ${
                  isCompleted
                    ? "bg-green-500 border-green-500 text-white"
                    : isActive
                      ? "bg-red-600 border-red-600 text-white shadow-lg shadow-red-600/40"
                      : "bg-transparent border-gray-500 text-gray-400"
                }`}
              >
                {isCompleted ? <Check size={18} /> : index + 1}
              </div>
              <span
                className={`mt-2 text-xs font-semibold tracking-wide transition-colors ${
                  isCompleted
                    ? "text-green-400"
                    : isActive
                      ? "text-white"
                      : "text-gray-500"
                }`}
              >
                {step.label}
              </span>
              <span
                className={`text-[10px] transition-colors ${
                  isActive ? "text-gray-300" : "text-gray-600"
                }`}
              >
                {step.description}
              </span>
            </div>

            {index < STEPS.length - 1 && (
              <div
                className={`w-20 h-0.5 mx-3 mb-8 transition-colors duration-300 ${
                  isCompleted ? "bg-green-500" : "bg-gray-600"
                }`}
              />
            )}
          </div>
        )
      })}
    </div>
  )
}
