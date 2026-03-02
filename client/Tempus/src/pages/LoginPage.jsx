import { useState } from "react";
import { useLogin } from "../hooks/useAuth";
import { Link } from "react-router-dom";

export default function LoginPage() {
  const { login, cargando } = useLogin();
  const [credentials, setCredentials] = useState({ email: "", password: "" });

  const handleSubmit = async (e) => {
    e.preventDefault();
    await login(credentials);
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 p-4">
      <div className="bg-white p-8 rounded-xl shadow-lg w-full max-w-sm space-y-6 border border-gray-100">
        <div className="text-center">
          <h2 className="text-3xl font-extrabold text-red-950">Tempus</h2>
          <p className="text-gray-500 text-sm mt-1">Ingresá a tu panel de horarios</p>
        </div>

        <form onSubmit={handleSubmit} className="space-y-4">
          <input 
            type="email" 
            placeholder="Email" 
            className="w-full border p-3 rounded-lg outline-none focus:ring-2 focus:ring-red-900"
            onChange={(e) => setCredentials({...credentials, email: e.target.value})}
            required 
          />
          <input 
            type="password" 
            placeholder="Contraseña" 
            className="w-full border p-3 rounded-lg outline-none focus:ring-2 focus:ring-red-900"
            onChange={(e) => setCredentials({...credentials, password: e.target.value})}
            required 
          />
          <button 
            type="submit" 
            disabled={cargando}
            className="w-full bg-red-950 text-white py-3 rounded-lg font-bold hover:bg-red-900 disabled:bg-gray-400 shadow-md transition-all"
          >
            {cargando ? "Validando..." : "Iniciar Sesión"}
          </button>
        </form>

        <p className="text-center text-sm text-gray-600">
          ¿No tenés cuenta? <Link to="/register" className="text-red-900 font-bold hover:underline">Registrate acá</Link>
        </p>
      </div>
    </div>
  );
}