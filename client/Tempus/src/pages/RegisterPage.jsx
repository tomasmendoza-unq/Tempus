import { useState, useEffect } from "react";
import { useRegistrarUsuario } from "../hooks/useAuth";
import { Link } from "react-router-dom";
import useCarrera from "../hooks/useCarrera";
import SelectField from "../components/Ui/Select/SelectField";
import AuthLayout from "../components/Auth/AuthLayout";
import AuthInput from "../components/Auth/AuthInput";
import AuthButton from "../components/Auth/AuthButton";

export default function RegisterPage() {
  const { registrar, cargando } = useRegistrarUsuario();
  const { recuperarCarreras } = useCarrera();

  const [carreras, setCarreras] = useState([]);
  const [carreraSeleccionada, setCarreraSeleccionada] = useState(null);

  const [formData, setFormData] = useState({
    email: "",
    password: "",
    nombre: "",
    apellido: "",
    telefono: ""
  });

  useEffect(() => {
    recuperarCarreras().then(setCarreras);
  }, []);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleCarreraSeleccion = ([nombreCarrera, idCarrera]) => {
    setCarreraSeleccionada({ nombreCarrera, idCarrera });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await registrar({ ...formData, carreraId: carreraSeleccionada?.idCarrera });
  };

  return (
    <AuthLayout title="Crear Cuenta Tempus">
      <form onSubmit={handleSubmit} className="space-y-4">

        <div className="grid grid-cols-2 gap-4">
          <AuthInput name="nombre" placeholder="Nombre" onChange={handleChange} />
          <AuthInput name="apellido" placeholder="Apellido" onChange={handleChange} />
        </div>

        <AuthInput name="email" type="email" placeholder="Email institucional" onChange={handleChange} />
        <AuthInput name="telefono" placeholder="Teléfono" onChange={handleChange} />
        <AuthInput name="password" type="password" placeholder="Contraseña" onChange={handleChange} />

        <SelectField
          label="Carrera"
          value={carreraSeleccionada?.idCarrera || ""}
          placeholder="Seleccioná una carrera"
          options={carreras.map(c => ({ 
            value: c.idCarrera, 
            label: c.nombreCarrera 
          }))}
          onChange={(value) => {
            const carrera = carreras.find((c) => c.idCarrera == value);
            setCarreraSeleccionada(carrera || null);
          }}
        />

        <AuthButton loading={cargando} text="Registrarse" loadingText="Registrando..." />

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