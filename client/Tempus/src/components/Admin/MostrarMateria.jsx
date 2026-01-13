import { traerMateria } from "../../hooks/materia";
import { useState } from "react";

export default function MostrarMateria() {
  const [materia, setMateria] = useState(null);
  const [response, setResponse] = useState(null);

  console.log(response);
  return (
    <form
      className="flex bg-white min-h-25 min-w-25  items-center justify-center flex-col gap-3.5 p-6 rounded"
      onSubmit={async (e) => {
        e.preventDefault();
        const data = await traerMateria(materia.nombre);
        setResponse(data);
      }}
    >
      <input
        onChange={(e) =>
          setMateria((prev) => ({
            ...prev,
            nombre: e.target.value,
          }))
        }
        value={materia ? materia.nombre : ""}
        placeholder="idMateria..."
      />
      <button
        className="mt-4 bg-red-950 text-white py-2 px-4 rounded"
        type="submit"
      >
        Buscar Materia
      </button>
    </form>
  );
}
