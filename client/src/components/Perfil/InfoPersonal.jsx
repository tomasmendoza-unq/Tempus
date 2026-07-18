
export function InfoPersonal({ nombre, apellido, email }) {
  return (
    <>
      <div className="border-b pb-4 text-center md:text-left">
        <h2 className="text-3xl font-extrabold text-red-950 italic">Mi Perfil</h2>
        <p className="text-gray-500 text-sm mt-1">Panel de control académico - Tempus</p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-6 bg-gray-50 p-6 rounded-xl border border-gray-100">
        <div className="flex flex-col">
          <span className="text-xs font-bold text-red-900 uppercase tracking-tighter">Alumno</span>
          <p className="text-lg text-gray-800 font-semibold">{nombre} {apellido}</p>
        </div>
        <div className="flex flex-col">
          <span className="text-xs font-bold text-red-900 uppercase tracking-tighter">Email</span>
          <p className="text-lg text-gray-800 font-semibold">{email}</p>
        </div>
      </div>
    </>
  );
}