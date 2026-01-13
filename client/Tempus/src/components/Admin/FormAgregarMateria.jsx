import { useState } from "react";
import { crearMateria } from "../../hooks/materia";

export default function FormAgregarMateria() {
  const [formData, setFormData] = useState({
    materiaNombre: "",
    correlativas: [],
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    crearMateria(formData);
  };

  return (
    <form
      className="flex items-center justify-center pt-10"
      onSubmit={handleSubmit}
    >
      <div className="flex items-center justify-center flex-col gap-3.5 bg-white rounded p-6">
        <label className="text-black">Nombre materia:</label>
        <input
          className="border border-gray-300 rounded p-2"
          placeholder="Materia"
          type="text"
          value={formData.materiaNombre}
          onChange={(e) => {
            const valor = e.target.value;
            setFormData((prev) => ({ ...prev, materiaNombre: valor }));
          }}
        />
        <button
          className="mt-4 bg-red-950 text-white py-2 px-4 rounded"
          type="submit"
        >
          Cargar materia
        </button>
      </div>
    </form>
  );
}
