import FormAgregarMateria from "../components/Admin/FormAgregarMateria";
import MostrarMateria from "../components/Admin/MostrarMateria";

export default function Materias() {
  return (
    <div className="flex items-center justify-evenly gap-5">
      <div className="space-y-2">
        <h2 className="text-xl font-semibold">Cargar materia</h2>
        <FormAgregarMateria />
      </div>
      <div className="space-y-2">
        <h2 className="text-xl font-semibold">Buscar materia</h2>
        <MostrarMateria />
      </div>
    </div>
  );
}
