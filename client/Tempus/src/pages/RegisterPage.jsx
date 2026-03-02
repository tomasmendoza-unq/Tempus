import { useState } from "react";
import { useRegistrarUsuario } from "../hooks/useAuth";

export default function RegisterPage() {
  const { registrar, cargando } = useRegistrarUsuario();
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    nombre: "",
    apellido: "",
    telefono: ""
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await registrar(formData);
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 p-4">
      <form onSubmit={handleSubmit} className="bg-white p-8 rounded-xl shadow-lg w-full max-w-md space-y-4 border border-gray-100">
        <h2 className="text-2xl font-bold text-red-950 text-center">Crear Cuenta Tempus</h2>
        
        <div className="grid grid-cols-2 gap-4">
          <input name="nombre" placeholder="Nombre" onChange={handleChange} required
            className="border p-2 rounded focus:ring-2 focus:ring-red-900 outline-none" />
          <input name="apellido" placeholder="Apellido" onChange={handleChange} required
            className="border p-2 rounded focus:ring-2 focus:ring-red-900 outline-none" />
        </div>

        <input name="email" type="email" placeholder="Email institucional" onChange={handleChange} required
          className="w-full border p-2 rounded focus:ring-2 focus:ring-red-900 outline-none" />
        
        <input name="telefono" type="text" placeholder="Teléfono" onChange={handleChange} required
          className="w-full border p-2 rounded focus:ring-2 focus:ring-red-900 outline-none" />

        <input name="password" type="password" placeholder="Contraseña" onChange={handleChange} required
          className="w-full border p-2 rounded focus:ring-2 focus:ring-red-900 outline-none" />

        <button type="submit" disabled={cargando}
          className="w-full bg-red-950 text-white py-2 rounded font-bold hover:bg-red-900 disabled:bg-gray-400 transition-all">
          {cargando ? "Registrando..." : "Registrarse"}
        </button>
      </form>
    </div>
  );
}