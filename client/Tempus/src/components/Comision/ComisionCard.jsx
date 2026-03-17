import HorarioList from "./HorarioList"

export default function ComisionCard({ comision, children }) {
  return (
    <div className="w-full max-w-xs flex flex-col justify-center items-center border-2 hover:scale-110 border-gray-950 bg-white py-5 text-black hover:shadow-md hover:shadow-red-700 hover:bg-red-300 duration-200 rounded-2xl">
      <h2 className="text-xl font-bold mb-2 text-red-500">
        Comision: #{comision?.comisionId || "Sin nombre"}
      </h2>
      <p className="text-lg mb-2 text-center px-4 line-clamp-2">
        {comision?.materia?.materiaNombre || "Sin nombre"}
      </p>
      <HorarioList horarios={comision?.claseHorario} />
      {children}
    </div>
  )
}