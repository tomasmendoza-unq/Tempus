import { useState } from "react";
import { useRegistrarUsuario } from "../hooks/useAuth";
import { Link } from "react-router-dom";

import AuthLayout from "../components/auth/AuthLayout";
import AuthInput from "../components/auth/AuthInput";
import AuthButton from "../components/auth/AuthButton";

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
    <AuthLayout title="Crear Cuenta Tempus">
      <form onSubmit={handleSubmit} className="space-y-4">

        <div className="grid grid-cols-2 gap-4">
          <AuthInput name="nombre" placeholder="Nombre" onChange={handleChange} />
          <AuthInput name="apellido" placeholder="Apellido" onChange={handleChange} />
        </div>

        <AuthInput
          name="email"
          type="email"
          placeholder="Email institucional"
          onChange={handleChange}
        />

        <AuthInput
          name="telefono"
          placeholder="Teléfono"
          onChange={handleChange}
        />

        <AuthInput
          name="password"
          type="password"
          placeholder="Contraseña"
          onChange={handleChange}
        />

        <AuthButton
          loading={cargando}
          text="Registrarse"
          loadingText="Registrando..."
        />

        <p className="text-center text-sm text-gray-600 pt-2">
          ¿Ya tenés cuenta?{" "}
          <Link to="/login" className="text-red-900 font-bold hover:underline">
            Iniciá sesión
          </Link>
        </p>

      </form>
    </AuthLayout>
  );
}