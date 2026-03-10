export default function Home() {
  return (
    <section className="flex flex-col items-center justify-center min-h-[70vh] text-center px-6">
      
      <h1 className="text-4xl md:text-5xl font-bold text-white tracking-tight">
        Bienvenido a <span className="text-red-500">Tempus</span>
      </h1>

      <p className="mt-4 text-lg text-gray-400 max-w-xl leading-relaxed">
        Gestiona tu recorrido académico en la UNQ.  
        Registra tus materias, explora el mapa de correlativas
        y planifica tu cursada para los próximos cuatrimestres.
      </p>

      <div className="mt-8 h-px w-40 bg-gradient-to-r from-transparent via-red-600 to-transparent" />

      <p className="mt-6 text-sm text-gray-500">
        Usa el menú para comenzar.
      </p>

    </section>
  );
}