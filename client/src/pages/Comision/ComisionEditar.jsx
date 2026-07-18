import { useParams } from "react-router-dom"
import { useComision } from "../../hooks/useComision"
import { useEffect, useState } from "react"
import { useFormComision } from "../../hooks/useComision"
import { parseBackendComisionToReducer } from "../../helpers/comision/comisionParsers"
import ComisionHorarioEditForm from "../../components/Comision/ComisionHorarioEditForm"
import { useTraerTodasMaterias } from "../../hooks/useMateria"
import SelectField from "../../components/Ui/Select/SelectField"
import Tabs from "../../components/Ui/Tabs/Tabs"
import Modal from "../../components/Ui/Modal/Modal"
import { useNavigate } from "react-router-dom"

const tabs = [
  { label: "Modificar comisión", id: "comision" },
  { label: "Modificar horarios", id: "horarios" },
]

export default function ComisionEditar() {
  const { id } = useParams()
  const { obtenerComision, editarComision, editarHorariosComision } =
    useComision()
  const {
    comision,
    cargando,
    setCargando,
    cargarComisionCompleta,
    setMateriaSeleccionada,
  } = useFormComision()
  const { traerMaterias, materias } = useTraerTodasMaterias()
  const navigate = useNavigate()

  useEffect(() => {
    const fetchComision = async () => {
      setCargando(true)
      const response = await obtenerComision(id)

      if (!response) {
        setCargando(false)
        return
      }

      const comisionParseada = parseBackendComisionToReducer(response)

      if (comisionParseada) {
        cargarComisionCompleta(comisionParseada)
      }

      setCargando(false)
    }

    if (id) {
      fetchComision()
    }
  }, [])

  useEffect(() => {
    traerMaterias()
  }, [])

  const [currentTab, setCurrentTab] = useState(0)
  const [modalOpen, setModalOpen] = useState(false)
  const [modalAction, setModalAction] = useState(null)

  const handleMateriaChange = (value) => {
    const materiaIdSeleccionada = Number(value)
    const materiaNueva = materias.find(
      (m) => m.materiaId === materiaIdSeleccionada
    )

    if (!materiaNueva) {
      return
    }

    if (materiaNueva.materiaId !== comision.materiaSeleccionada?.materiaId) {
      setMateriaSeleccionada(materiaNueva)
    }
  }

  const handleGuardarMateria = () => {
    setModalAction("materia")
    setModalOpen(true)
  }

  const confirmarGuardarMateria = () => {
    editarComision(id, comision.materiaSeleccionada?.materiaId)
    setModalOpen(false)
  }

  const handleGuardarHorarios = () => {
    setModalAction("horarios")
    setModalOpen(true)
  }

  const confirmarGuardarHorarios = () => {
    editarHorariosComision(comision.comision.horarios)
    setModalOpen(false)
  }

  return (
    <div className="flex flex-col justify-center items-center gap-5">
      <h1 className="text-2xl font-bold mb-4 text-white">
        Editar Comisión: {id}
      </h1>
      <button
        onClick={() => navigate("/comisiones/mostrar")}
        className="self-start text-sm text-gray-300 hover:text-white border border-gray-600 hover:border-gray-400 px-4 py-2 rounded-lg transition-colors"
      >
        Volver a listado
      </button>
      <Tabs tabs={tabs} currentTab={currentTab} onTabChange={setCurrentTab} />
      {cargando ? (
        <p>Cargando comisión...</p>
      ) : (
        <>
          {currentTab === 0 && (
            <div className="w-full max-w-xl rounded-xl border border-white/10 bg-white/5 p-5 backdrop-blur-sm">
              <div className="mb-4">
                <p className="text-sm text-gray-300 mb-1">
                  <span className="font-semibold text-gray-100">
                    Comisión actual:
                  </span>{" "}
                  #{id}
                </p>
                <p className="text-xs text-gray-400">
                  Seleccioná la nueva materia para que tome esta comisión. La
                  comisión se transferirá de su materia actual.
                </p>
              </div>
              <label className="mb-2 block text-sm font-semibold text-gray-200">
                Materia de la comisión
              </label>
              <SelectField
                value={comision.materiaSeleccionada?.materiaId ?? ""}
                onChange={handleMateriaChange}
                options={materias.map((materia) => ({
                  value: materia.materiaId,
                  label: materia.materiaNombre,
                }))}
                placeholder="Seleccioná una materia"
              />
              <button
                className="mt-4 w-full rounded-lg bg-red-950 px-4 py-2.5 font-semibold text-white transition-all hover:bg-red-900 active:scale-95 focus:outline-none focus:ring-2 focus:ring-red-600 focus:ring-offset-1 focus:ring-offset-gray-950 disabled:opacity-50 disabled:cursor-not-allowed"
                onClick={handleGuardarMateria}
              >
                Guardar cambios
              </button>
            </div>
          )}
          {currentTab === 1 && (
            <div className="w-full flex flex-col items-center gap-4">
              <ComisionHorarioEditForm
                titulo={`Modificar horarios de cursada para ${comision.materiaSeleccionada?.materiaNombre || "la materia"}`}
              />
              <button
                className="mt-2 w-full max-w-2xl rounded-lg bg-red-950 px-4 py-2.5 font-semibold text-white transition-all hover:bg-red-900 active:scale-95 focus:outline-none focus:ring-2 focus:ring-red-600 focus:ring-offset-1 focus:ring-offset-gray-950 disabled:opacity-50 disabled:cursor-not-allowed"
                onClick={handleGuardarHorarios}
              >
                Guardar horarios
              </button>
            </div>
          )}
        </>
      )}

      <Modal isOpen={modalOpen} onClose={() => setModalOpen(false)}>
        <div className="flex flex-col items-center justify-center gap-4">
          {modalAction === "materia" && (
            <>
              <h2 className="text-2xl font-bold text-gray-900">
                Confirmar cambio de materia
              </h2>
              <p className="text-gray-600">
                ¿Estás seguro de que querés reasignar la comisión #{id} a{" "}
                <span className="font-semibold">
                  {comision.materiaSeleccionada?.materiaNombre}
                </span>
                ?
              </p>
              <p className="text-sm text-gray-500">
                La comisión se transferirá de su materia actual.
              </p>
              <div className="flex gap-3 justify-end mt-4">
                <button
                  onClick={() => setModalOpen(false)}
                  className="px-6 py-2.5 border border-gray-300 rounded-lg text-gray-700 font-semibold hover:bg-gray-50 transition-colors"
                >
                  Cancelar
                </button>
                <button
                  onClick={confirmarGuardarMateria}
                  className="px-6 py-2.5 bg-red-950 hover:bg-red-900 text-white font-semibold rounded-lg transition-colors"
                >
                  Guardar
                </button>
              </div>
            </>
          )}

          {modalAction === "horarios" && (
            <>
              <h2 className="text-2xl font-bold text-gray-900">
                Confirmar cambios de horarios
              </h2>
              <p className="text-gray-600">
                ¿Estás seguro de que querés guardar los cambios en los horarios
                de la comisión?
              </p>
              <div className="flex gap-3 justify-end mt-4">
                <button
                  onClick={() => setModalOpen(false)}
                  className="px-6 py-2.5 border border-gray-300 rounded-lg text-gray-700 font-semibold hover:bg-gray-50 transition-colors"
                >
                  Cancelar
                </button>
                <button
                  onClick={confirmarGuardarHorarios}
                  className="px-6 py-2.5 bg-red-950 hover:bg-red-900 text-white font-semibold rounded-lg transition-colors"
                >
                  Guardar
                </button>
              </div>
            </>
          )}
        </div>
      </Modal>
    </div>
  )
}
