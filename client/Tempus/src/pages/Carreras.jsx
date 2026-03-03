import CarreraForm from "../components/Carrera/CarreraForm"
export default function Carreras() {
  return (
    <div className="flex flex-col justify-center items-center">
      <h1 className="text-2xl font-bold mb-4 text-white">
        Gestión de Carreras
      </h1>
      <CarreraForm />
    </div>
  )
}
